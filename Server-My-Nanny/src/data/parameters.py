class BaseParameter:
    def __init__(self) -> None:
        super().__init__()
        self.data_json = None
        self.language = None

    def from_json(self, data_json):
        self.data_json = data_json
        self.language = data_json["language"]
        return self

    def to_json(self):
        return self.data_json


class CreateUserParameter(BaseParameter):

    def __init__(self) -> None:
        super().__init__()
        self.name = None
        self.email = None
        self.password = None

    def from_json(self, data_json):
        super().from_json(data_json)
        self.name = data_json.get("name", None)
        self.password = data_json.get("password", None)
        self.email = data_json.get("email", None)
        return self


class AuthParameter(BaseParameter):

    def __init__(self) -> None:
        super().__init__()
        self.email = None
        self.password = None

    def from_json(self, data_json):
        super().from_json(data_json)
        self.email = data_json["email"]
        self.password = data_json["password"]
        return self


class UpdatePasswordParameter(BaseParameter):
    def __init__(self) -> None:
        super().__init__()
        self.password = None
        self.new_password = None

    def from_json(self, data_json):
        super().from_json(data_json)
        self.password = data_json["password"]
        self.new_password = data_json["new_password"]
        return self
