from flask_socketio import SocketIO

socketio = SocketIO()

def init_socketio(app):
    socketio.init_app(app, cors_allowed_origins="*")
    
    # Import socket events after socketio initialization
    from app.socket import events 