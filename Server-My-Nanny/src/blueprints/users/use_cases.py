from src.blueprints.default import UseCase
from src.db.user_dao import UserDao
from src.models.user_dto import UserDTO


class UserUseCase(UseCase):

    def __init__(self) -> None:
        super().__init__()
        self.db = UserDao()

    def list_all_user(self):
        users = self.db.list_all_users()
        return self.response_success({
            "users": users
        })

    def create_new_user(self, data_json):
        new_user = UserDTO(data_json)
        user_db = self.db.get_user({"email": new_user.email})

        if user_db.user_id:
            return self.response_failed({
                'message': self.translate_string('user already exists', new_user.language)
            })

        new_user.generate_user_id()
        new_user.hash_password()

        self.db.create_user(new_user)

        return self.response_success({
            'message': self.translate_string('user created', new_user.language)
        })

    def update_user(self, data_json, user_id):
        query = {"user_id": user_id}
        user = UserDTO(data_json)
        user_db = self.db.get_user(query)

        if not user_db.user_id:
            return self.response_failed({
                'message': self.translate_string('user not found', user.language)
            })

        user_db.update_from_user(user)
        self.db.update_user(query=query, fields=user_db.to_json())

        return self.response_success({'message': self.translate_string('user updated successfully', user.language)})
