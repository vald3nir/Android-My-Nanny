from src.data.database import AppDB
from src.data.dtos import UserDTO


class UserDao(AppDB):

    def __init__(self) -> None:
        super().__init__(collection="users")

    def create_user(self, new_user):
        self.insert_one(new_user.to_json())

    def update_user(self, query, fields):
        self.update(query=query, new_value={"$set": fields})

    def get_user(self, query):
        return UserDTO().from_json(self.find_one(query))

    def list_all_users(self):
        users = []
        for data_json in self.find_all({}):
            users.append(UserDTO().get_json_formatted(data_json=data_json))
        return users
