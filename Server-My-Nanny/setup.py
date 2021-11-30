# coding=utf-8
# !/usr/bin/python3

from subprocess import call

libraries = [
    # Flask libraries
    "Flask", "Flask-Cors",
    # Database
    "pymongo[srv]",
    # Web Token
    "jwt",
    # Translate strings
    "googletrans==4.0.0-rc1",
]

call("pip install --upgrade " + ' '.join(libraries), shell=True)
call("pip freeze > requirements.txt", shell=True)
