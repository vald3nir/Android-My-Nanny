# coding=utf-8
# !/usr/bin/python3

import os

from flask import Flask
from flask_cors import CORS

import src.utils.auth_utils as auth
from src.blueprints import blueprints

# Flask config
# ---------------------------------------------------------------

app = Flask(__name__)

app.config['FLASK_ENV'] = 'development'
app.config['CORS_HEADERS'] = 'Content-Type'
app.config['SECRET_KEY'] = auth.SECRET_KEY
app.config['TESTING'] = True
app.config['DEBUG'] = True

# Cors
# ---------------------------------------------------------------
cors = CORS(app, resources={r"/*": {"origins": "*"}})

# Blueprints config
# ---------------------------------------------------------------

for blueprint in blueprints:
    app.register_blueprint(blueprint)

# Start server
# ---------------------------------------------------------------
port = int(os.environ.get('PORT', 5000))
if __name__ == "__main__":
    app.run(host='0.0.0.0', port=port)
