from flask import Flask
from app.routes import register_routes
from app.database import init_db
from app.socket import init_socketio, socketio

def create_app():
    app = Flask(__name__)
    # Register all routes
    register_routes(app)
    
    # Initialize SocketIO
    init_socketio(app)
    
    return app 