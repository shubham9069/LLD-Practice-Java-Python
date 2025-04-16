import logging
from logging.handlers import RotatingFileHandler
import os

def setup_logger():
    # Create logs directory if it doesn't exist
    if not os.path.exists('logs'):
        os.makedirs('logs')
    
    # Configure logger
    logger = logging.getLogger('process_logger')
    logger.setLevel(logging.INFO)
    
    # Create rotating file handler
    handler = RotatingFileHandler(
        'logs/process.log',
        maxBytes=1024 * 1024,  # 1MB
        backupCount=5
    )
    
    # Create formatter
    formatter = logging.Formatter(
        '%(asctime)s - %(levelname)s - %(message)s'
    )
    handler.setFormatter(formatter)
    
    # Add handler to logger
    logger.addHandler(handler)
    
    return logger 

def logs_save(array, file_name):
    os.makedirs(os.path.dirname(file_name), exist_ok=True)    
    
    with open(file_name, 'a') as f:
        for item in array:
            f.write(item + '\n')