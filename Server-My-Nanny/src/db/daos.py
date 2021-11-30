# coding=utf-8
# !/usr/bin/python3

from src.db.mongodb import MongoDB


class UserDao(MongoDB):
    def __init__(self) -> None:
        super().__init__(collection="users")
