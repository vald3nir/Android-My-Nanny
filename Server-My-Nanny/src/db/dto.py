class UserDTO:

    def __init__(self, user_id, name, password, email, created_at) -> None:
        super().__init__()
        self.user_id = user_id
        self.name = name
        self.password = password
        self.email = email
        self.created_at = created_at

    def to_json(self):
        return {
            "user_id": self.user_id,
            "name": self.name,
            "password": self.password,
            "email": self.email,
            "created_at": self.created_at
        }
