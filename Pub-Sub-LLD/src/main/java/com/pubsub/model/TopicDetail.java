package com.pubsub.model;
import java.util.HashSet;
import java.util.Set;

import com.pubsub.core.Subscriber;



public class TopicDetail {
    public String topicName;
    private Set<Subscriber> subscribers;

    public TopicDetail(String topicName) {
        this.topicName = topicName;
        this.subscribers = new HashSet<>();
        System.out.println("Topic created: " + topicName);
    }

    public String getTopicName() {
        return topicName;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
        System.out.println("new subscriber added to topic: " + topicName);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void sendMessageToSubscribers(Message message) {
        System.out.println("Sending message to All subscribers: "+ subscribers.size());
        subscribers.forEach(subscriber -> subscriber.receiveMessage(message));
    }

   
}
