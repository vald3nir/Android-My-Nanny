from datetime import datetime, timedelta


def get_today_date_formatted():
    return datetime.today().strftime('%Y-%m-%d')


def get_today_date_utc():
    return str(datetime.utcnow())


def create_data_exp():
    return datetime.utcnow() + timedelta(minutes=60 * 60 * 60)
