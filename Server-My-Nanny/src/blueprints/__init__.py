from src.blueprints.auth_blueprint import auth_blueprint
from src.blueprints.home_blueprint import home_blueprint
from src.blueprints.user_blueprint import user_blueprint

# ------------------------------------------------------------------
# Blueprints
# ------------------------------------------------------------------

blueprints = [
    home_blueprint,
    auth_blueprint,
    user_blueprint
]
