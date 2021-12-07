from src.blueprints.default import UseCase
from src.db.user_dao import UserDao
from src.models.login_dto import LoginDTO
from src.utils.auth_utils import compare_password_hash, generate_user_token


class AuthUseCase(UseCase):

    def __init__(self) -> None:
        super().__init__()
        self.db = UserDao()

    def login(self, data_json):
        login_dto = LoginDTO().from_json(data_json)

        user_db = self.db.get_user({"email": login_dto.email})

        if user_db.user_id is None:
            return self.response_failed(
                {
                    'message': self.translate_string('user not found', login_dto.language)
                }
            )

        if not compare_password_hash(user_db.password, login_dto.password):
            return self.response_failed(
                {
                    'message': self.translate_string('password wrong', login_dto.language)
                }
            )

        return self.response_success({
            'user_id': user_db.user_id,
            'token': generate_user_token(user_db.user_id)
        })

# --------------------------------------------------------------------


# def update_user_password(data_json, user_id):
#     language = data_json.get("language", None)
#     db = UserDao()
#
#     try:
#
#         user_db = db.find_one({"user_id": user_id})
#
#         if not compare_password_hash(user_db["password"], data_json["password"]):
#             return response_password_wrong(language)
#
#         db.update(
#             query={"user_id": user_id}, new_value={
#                 "$set": {
#                     "password": create_password_hash(data_json['new_password'])
#                 }
#             })
#         return response_password_updated(language)
#
#     except Exception as e:
#         print(e)
#
#     return response_user_not_found(language)
