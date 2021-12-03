import uuid
import jwt
from werkzeug.security import generate_password_hash, check_password_hash

from src.utils.date_utils import create_data_exp

SECRET_KEY = 'Th1s1ss3cr3t'


def create_user_id():
    return 'user_' + str(uuid.uuid4())


def create_password_hash(password):
    return generate_password_hash(password, method='sha256')


def compare_password_hash(p1, p2):
    return check_password_hash(p1, p2)


def generate_user_token(user_id):
    token = jwt.encode({
        'user_id': user_id,
        'exp': create_data_exp()
    }, SECRET_KEY)
    return token.decode('UTF-8')
