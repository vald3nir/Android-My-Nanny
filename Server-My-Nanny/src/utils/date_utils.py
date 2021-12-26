from datetime import datetime, timedelta


def get_today_date_formatted():
    return datetime.today().strftime('%Y-%m-%d')


def get_today_date_utc():
    return str(datetime.utcnow())


def get_date_from_timestamp(timestamp):
    return datetime.fromtimestamp(timestamp)


def create_data_exp():
    return datetime.utcnow() + timedelta(minutes=30)
