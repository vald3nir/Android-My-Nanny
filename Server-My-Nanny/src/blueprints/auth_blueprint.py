from functools import wraps

from flask import Blueprint, request

import src.utils.auth_utils as auth_utils
import src.utils.response_utils as response_utils
from src.data.daos import UserDao
from src.data.parameters import AuthParameter, UpdatePasswordParameter, BaseParameter

# ------------------------------------------------------------------------
# setup
# ------------------------------------------------------------------------

auth_blueprint = Blueprint('auth_blueprint', __name__)
user_dao = UserDao()


# ------------------------------------------------------------------------
# token validate
# ------------------------------------------------------------------------

def token_required(f):
    @wraps(f)
    def decorated(*args, **kwargs):
        token = request.args.get('token')
        base_dto = BaseParameter().from_json(request.get_json())
        if not token:
            return response_utils.response_token_invalid(base_dto.language)
        try:
            user_id = auth_utils.user_id_from_token(token)
            return f(user_id, *args, **kwargs)
        except:
            return response_utils.response_token_expired(base_dto.language)

    return decorated


# ------------------------------------------------------------------------
# routers
# ------------------------------------------------------------------------

@auth_blueprint.route('/login', methods=['POST'])
def route_login():
    """
    body:
    {
        "password": "12345678",
        "email": "severino@gmail.com",
        "language": "pt"
    }
    """
    data_json = request.get_json()
    return login(auth_parameter=AuthParameter().from_json(data_json))


@auth_blueprint.route('/update_password', methods=['PUT'])
@token_required
def router_update_user_password(user_id):
    """
    parameters:
    header:
    {
        "token": "xxxxxxxxxxxxxxxxxxxxxxx"
    }
    body:
    {
        "password": "11111111",
        "new_password": "12345678",
        "language": "pt"
    }
    """
    data_json = request.get_json()
    return update_user_password(
        update_password_parameter=UpdatePasswordParameter().from_json(data_json),
        user_id=user_id
    )


# ------------------------------------------------------------------------
# features
# ------------------------------------------------------------------------

def login(auth_parameter):
    user_db = user_dao.get_user({"email": auth_parameter.email})

    if not user_db.was_created():
        return response_utils.response_user_not_found(auth_parameter.language)

    if not auth_utils.compare_password_hash(user_db.password, auth_parameter.password):
        return response_utils.response_password_wrong(auth_parameter.language)

    return response_utils.response_success({
        'token': auth_utils.generate_user_token(user_db.user_id)
    })


def update_user_password(update_password_parameter, user_id):
    user_db = user_dao.get_user({"user_id": user_id})

    if not user_db.was_created():
        return response_utils.response_user_not_found(update_password_parameter.language)

    if not auth_utils.compare_password_hash(user_db.password, update_password_parameter.password):
        return response_utils.response_password_wrong(update_password_parameter.language)

    user_dao.update(
        query={"user_id": user_id}, new_value={
            "$set": {
                "password": auth_utils.create_password_hash(update_password_parameter.new_password)
            }
        })
    return response_utils.response_password_updated(update_password_parameter.language)
