package com.pubsub.broker;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import com.pubsub.core.Subscriber;
import com.pubsub.model.Message;

public class MessageBroker {
    private static MessageBroker instance;
    private final ConcurrentHashMap<String, CopyOnWriteArraySet<Subscriber>> topicSubscribers;

    private MessageBroker() {
        this.topicSubscribers = new ConcurrentHashMap<>();
    }

    public static synchronized MessageBroker getInstance() {
        if (instance == null) {
            instance = new MessageBroker();
        }
        return instance;
    }

    public void publish(String topicName, Message message) {
        CopyOnWriteArraySet<Subscriber> subscribers = topicSubscribers.get(topicName);
        if (subscribers != null) {
            System.out.println("MessageBroker: Broadcasting message to " + subscribers.size() + 
                             " subscribers for topic: " + topicName);
            subscribers.forEach(subscriber -> subscriber.receiveMessage(message));
        } else {
            System.out.println("MessageBroker: No subscribers found for topic: " + topicName);
        }
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        topicSubscribers.computeIfAbsent(topicName, k -> new CopyOnWriteArraySet<>()).add(subscriber);
        System.out.println("MessageBroker: New subscriber added to topic: " + topicName);
    }

    public void unsubscribe(String topicName, Subscriber subscriber) {
        CopyOnWriteArraySet<Subscriber> subscribers = topicSubscribers.get(topicName);
        if (subscribers != null) {
            subscribers.remove(subscriber);
            System.out.println("MessageBroker: Subscriber removed from topic: " + topicName);
        }
    }
} 