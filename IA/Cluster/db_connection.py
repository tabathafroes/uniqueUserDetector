import dj_database_url
import psycopg2
import db_config as config

def get_con():
    read_con = psycopg2.connect(database=config.NAME,
                                user=config.USER,
                                password=config.PASSWORD,
                                host=config.HOST,
                                cursor_factory=psycopg2.extensions.cursor)

    return read_con

def get_write_con():
    write_con = psycopg2.connect(database=config.NAME,
                                user=config.USER,
                                password=config.PASSWORD,
                                host=config.HOST)

    return write_con