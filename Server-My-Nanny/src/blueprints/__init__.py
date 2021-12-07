from src.blueprints.auth import auth_blueprint
from src.blueprints.users import user_blueprint

# ------------------------------------------------------------------
# Blueprints
# ------------------------------------------------------------------

blueprints = [auth_blueprint, user_blueprint]
