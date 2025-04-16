from app.socket import socketio
from flask_socketio import emit, join_room
from flask import request
import json
import os


# Add a dictionary to store connected users
connected_users = {}
@socketio.on('connect')
def handle_connect():
    print('Client connected')
    socketio.emit('connection_response', {'data': f'Connected successfully!'})
    
@socketio.on('join_room_logs')
def handle_join_room(data):
    logs_room_id = data['logs_room_id']
    join_room(logs_room_id)
    socketio.emit('connection_response', {'data': f'Joined room {logs_room_id}'})

def send_logs(logs_room_id):
    if logs_room_id:
        data = read_logs()
        socketio.emit('logs_from_backend', {'logs': data}, to=logs_room_id)
    else:
        print(f'Room ID for user {logs_room_id} not found')


def read_logs():
    last_lines = read_last_n_lines('logs/user_logs.log', 10)
    return last_lines
        

def find_room_id(user_id):
    return connected_users[user_id]

def read_last_n_lines(filename, n=10):
    with open(filename, 'rb') as file:
        file.seek(0, os.SEEK_END)  # Move to the end of file
        position = file.tell()  # Get last byte position
        buffer = bytearray()
        lines = []

        while position > 0 and len(lines) < n:
            position -= 1
            file.seek(position)
            char = file.read(1)

            if char == b'\n':  # New line found
                if buffer:
                    lines.append(buffer[::-1].decode())  # Reverse buffer to get correct line
                    buffer.clear()
            else:
                buffer.extend(char)

        if buffer and len(lines) < n:  # Last line if file doesn't end with '\n'
            lines.append(buffer[::-1].decode())

    return lines[::-1]  


    
    
