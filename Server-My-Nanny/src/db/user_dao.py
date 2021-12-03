from src.db import AppDB
from src.models.user_dto import UserDTO


class UserDao:

    def __init__(self) -> None:
        super().__init__()
        self.db = AppDB(collection="users")

    def create_user(self, new_user):
        self.db.insert_one(new_user.to_json())

    def update_user(self, query, fields):
        self.db.update(query=query, new_value={"$set": fields})

    def get_user(self, query):
        user_data = self.db.find_one(query)
        return UserDTO(user_data)

    def list_all_users(self):
        users = []
        for data_json in self.db.find_all({}):
            users.append(UserDTO(data_json).to_json())
        return users
