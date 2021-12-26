from flask import Blueprint, request

import src.utils.response_utils as response_utils
from src.data.daos import UserDao
from src.data.dtos import UserDTO
from src.data.parameters import CreateUserParameter

# ------------------------------------------------------------------------
# setup
# ------------------------------------------------------------------------

user_blueprint = Blueprint('user_blueprint', __name__)

user_dao = UserDao()


# ------------------------------------------------------------------------
# routers
# ------------------------------------------------------------------------

@user_blueprint.route('/users', methods=['GET'])
def router_list_all_user():
    return list_all_user()


@user_blueprint.route('/users', methods=['POST'])
def router_create_new_user():
    """
    body:
    {
        "name": "vald3nir",
        "password": "12345678",
        "email": "vald3nir@gmail.com",
        "language": "pt"
    }
    """
    data_json = request.get_json()
    return create_new_user(CreateUserParameter().from_json(data_json))


# ------------------------------------------------------------------------
# features
# ------------------------------------------------------------------------


def list_all_user():
    users = user_dao.list_all_users()
    return response_utils.response_success({
        "users": users
    })


def create_new_user(create_user_parameter: CreateUserParameter):
    user_db = user_dao.get_user({"email": create_user_parameter.email})

    if user_db.was_created():
        return response_utils.response_user_already_exists(create_user_parameter.language)

    new_user = UserDTO().from_json(create_user_parameter.to_json())
    new_user.generate_user_id()
    new_user.create_hash_password()

    user_dao.create_user(new_user)

    return response_utils.response_user_created(create_user_parameter.language)
