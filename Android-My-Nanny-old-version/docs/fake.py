import datetime
from random import randint

from pymongo import MongoClient

# noinspection PyInterpreter
connection_params = {
    'user': 'vald3nir',
    'password': 'Vald3nir!',
    'host': 'ds125381.mlab.com',
    'port': 25381,
    'namespace': 'my_nanny',
}

connection = MongoClient(
    'mongodb://{user}:{password}@{host}:'
    '{port}/{namespace}'.format(**connection_params)
)

reports = connection.my_nanny.reports
reports.drop()

timestamp = datetime.datetime(2019, 6, 11, 10, 0, 0)

temp_baby = []
temp_env = []
light = []
humidity = []

for i in range(0, (60 * 8) / 50):
    temp_baby.append({
        "value": randint(36, 38),
        "data": str(timestamp)
    })

    temp_env.append({
        "value": randint(20, 24),
        "data": str(timestamp)
    })

    light.append({
        "value": randint(0, 1),
        "data": str(timestamp)
    })

    humidity.append({
        "value": randint(80, 100),
        "data": str(timestamp)
    })

    timestamp = timestamp + datetime.timedelta(seconds=(60 * 10))

reports.insert_one(
    {
        "temp_baby": temp_baby,
        "temp_env": temp_env,
        "light": light,
        "humidity": humidity
    }
)
print "game over!"
