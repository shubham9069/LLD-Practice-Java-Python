from flask import Blueprint, jsonify, request
import time
import random
from app.utils.logger import setup_logger, logs_save
from app.socket.events import  send_logs

process_bp = Blueprint('process', __name__)
logger = setup_logger()

@process_bp.route('/process', methods=['POST'])
def process_task():
    # Get iteration from query parameter, default to 1 if not provided
    iteration = request.args.get('iteration', default=1, type=int)
    logs_room_id = request.args.get('logs_room_id')
    
    # Simulate some processing steps
    while iteration > 0:
        steps = ['Initializing', 'Processing', 'Validating', 'Completing']
        result = []
        
        for step in steps:
            # Simulate processing time
            processing_time = random.uniform(0.1, 0.5)
            time.sleep(processing_time)
            
            # Log the step
            message = f"{step} step completed in {processing_time:.2f} seconds"
            logger.info(message)
            result.append(message)
            
            # Send real-time update via WebSocket
        
        logs_save(result, 'logs/user_logs.log')
        send_logs(logs_room_id)
        iteration -= 1
    
    return jsonify({
        "status": "success",
        "message": "Processing completed",
        "details": result
    }), 200 