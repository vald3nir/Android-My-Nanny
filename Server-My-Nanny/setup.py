# coding=utf-8
# !/usr/bin/python3

from subprocess import call

libraries = [
    "Flask", "Flask-Cors", "PyJWT", "pymongo[srv]", "googletrans==4.0.0-rc1",
]

call("pip install --upgrade " + ' '.join(libraries), shell=True)
call("pip freeze > requirements.txt", shell=True)
