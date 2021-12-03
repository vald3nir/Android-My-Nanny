# coding=utf-8
# !/usr/bin/python3

import pymongo


class _MongoDB:
    def __init__(self, client_url, database, collection) -> None:
        super().__init__()
        self.collection = pymongo.MongoClient(client_url)[database][collection]

    def find_one(self, query):
        return self.collection.find_one(query)

    def update(self, query, new_value):
        self.collection.update_one(query, new_value, upsert=True)

    def insert_one(self, document):
        self.collection.insert_one(document=document)

    def insert_many(self, documents):
        self.collection.insert_many(documents=documents)

    def aggregate(self, pipeline):
        return self.collection.aggregate(pipeline=pipeline)

    def find_all(self, query=None):
        if query is None:
            query = {}
        return list(self.collection.find(query))

    def last(self, limit=5):
        return list(self.collection.find().sort([('_id', pymongo.DESCENDING)]).limit(limit))

    def clear(self):
        self.collection.drop()

    def remove(self, query):
        self.collection.remove(query)


# Class used in the project
class AppDB(_MongoDB):

    def __init__(self, collection) -> None:
        _DATABASE = 'my_nanny'

        '''
          # use remote
          _DB_USER_NAME = "XXX"
          _DB_USER_PASSWORD = "XXX"
          _DB_HOST = f"XXX.mongodb.net/{_DATABASE}?retryWrites=true&w=majority"
          _CLIENT_URL = f"mongodb+srv://{_DB_USER_NAME}:{_DB_USER_PASSWORD}@{_DB_HOST}"
        '''

        # use local
        _DB_USER_NAME = "dev"
        _DB_USER_PASSWORD = "1721"
        _DB_HOST = "127.0.0.1"
        _CLIENT_URL = f"mongodb://{_DB_USER_NAME}:{_DB_USER_PASSWORD}@{_DB_HOST}"

        super().__init__(_CLIENT_URL, _DATABASE, collection)
