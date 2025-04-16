package com.pubsub.core;

import com.pubsub.broker.MessageBroker;
import com.pubsub.model.Message;

public class Publisher {
    private final MessageBroker broker;
    private final String publisherId;

    public Publisher(String publisherId) {
        this.broker = MessageBroker.getInstance();
        this.publisherId = publisherId;
    }

    public void publishMessage(String topicName, String content) {
        Message message = new Message(content);
        System.out.println("Publisher " + publisherId + ": Publishing message to topic: " + topicName);
        broker.publish(topicName, message);
    }
} 