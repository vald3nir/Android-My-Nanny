from src.utils.auth_utils import create_user_id, create_password_hash
from src.utils.date_utils import get_today_date_utc


class UserDTO:

    def __init__(self, data_json=None) -> None:
        super().__init__()

        if data_json is None:
            data_json = {}

        self.user_id = data_json.get("user_id", None)
        self.name = data_json.get("name", None)
        self.password = data_json.get("password", None)
        self.email = data_json.get("email", None)
        self.created_at = data_json.get("created_at", None)
        self.language = data_json.get("language", None)

    def update_from_user(self, user):
        self.name = user.name
        self.email = user.email

    def generate_user_id(self):
        self.user_id = create_user_id()
        self.created_at = get_today_date_utc()

    def hash_password(self):
        self.password = create_password_hash(self.password)

    def to_json(self):
        return {
            "user_id": self.user_id,
            "name": self.name,
            "password": self.password,
            "email": self.email,
            "created_at": self.created_at
        }
