from functools import wraps
from flask import request, jsonify

def request_logger(f):
    @wraps(f)
    def decorated_function(*args, **kwargs):
        # Log request info
        print(f"Request Method: {request.method}")
        print(f"Request Path: {request.path}")
        return f(*args, **kwargs)
    return decorated_function 