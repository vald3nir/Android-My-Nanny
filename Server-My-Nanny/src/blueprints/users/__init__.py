from flask import Blueprint, request

from src.blueprints.users.use_cases import UserUseCase

user_blueprint = Blueprint('user_blueprint', __name__)

use_case = UserUseCase()


@user_blueprint.route('/users', methods=['GET'])
def router_list_all_user():
    return use_case.list_all_user()


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
    return use_case.create_new_user(data_json=request.get_json())


@user_blueprint.route('/users/<user_id>', methods=['PUT'])
def router_update_user(user_id):
    """
    parameters:
    "user_id" : "user_524347ed-8312-4ee7-98dd-5aedc292c485"
    body:
    {
        "name": "vald3nir",
        "email": "vald3nir@gmail.com",
        "language": "pt"
    }
    """
    return use_case.update_user(data_json=request.get_json(), user_id=user_id)
