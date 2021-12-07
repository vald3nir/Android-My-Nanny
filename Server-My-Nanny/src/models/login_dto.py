class LoginDTO:

    def __init__(self) -> None:
        super().__init__()
        self.language = None
        self.email = None
        self.password = None

    def from_json(self, data_json):
        self.language = data_json.get("language", None)
        self.email = data_json["email"]
        self.password = data_json["password"]
        return self
