import uuid

import jwt
from werkzeug.security import generate_password_hash, check_password_hash

from src.utils.date_utils import *

SECRET_KEY = 'Application-Secret-Key'


def create_user_id():
    return 'user_' + str(uuid.uuid4())


def create_password_hash(password):
    return generate_password_hash(password, method='sha256')


def compare_password_hash(p1, p2):
    return check_password_hash(p1, p2)


def generate_user_token(user_id):
    payload = {
        'user_id': user_id,
        'exp': create_data_exp()
    }
    return jwt.encode(
        payload,
        SECRET_KEY,
        algorithm='HS256'
    )


def user_id_from_token(token):
    data = jwt.decode(
        token,
        SECRET_KEY,
        algorithms='HS256'
    )
    return data["user_id"]
