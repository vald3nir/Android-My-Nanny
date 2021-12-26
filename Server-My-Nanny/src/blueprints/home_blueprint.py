from flask import Blueprint

from src.utils.response_utils import response_success

# ------------------------------------------------------------------------
# setup
# ------------------------------------------------------------------------

home_blueprint = Blueprint('home_blueprint', __name__)


# ------------------------------------------------------------------------
# routers
# ------------------------------------------------------------------------

@home_blueprint.route('/')
def home():
    return response_success({"hello": "world"})
