package com.pubsub.core;

import com.pubsub.broker.MessageBroker;
import com.pubsub.model.Message;

public class Subscriber {
    private final MessageBroker broker;
    private final String subscriberId;

    public Subscriber(String subscriberId) {
        this.broker = MessageBroker.getInstance();
        this.subscriberId = subscriberId;
    }

    public void subscribe(String topicName) {
        System.out.println("Subscriber " + subscriberId + ": Subscribing to topic: " + topicName);
        broker.subscribe(topicName, this);
    }

    public void unsubscribe(String topicName) {
        System.out.println("Subscriber " + subscriberId + ": Unsubscribing from topic: " + topicName);
        broker.unsubscribe(topicName, this);
    }

    public void receiveMessage(Message message) {
        System.out.println("Subscriber " + subscriberId + ": Received message: " + message.getMessage());
    }
}
