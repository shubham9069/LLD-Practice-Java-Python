package com.pubsub;

import com.pubsub.core.Publisher;
import com.pubsub.core.Subscriber;

public class Main {
    public static void main(String[] args) {
        // Create publisher and subscribers
        Publisher publisher = new Publisher("Publisher-1");
        Subscriber subscriber1 = new Subscriber("Subscriber-1");
        Subscriber subscriber2 = new Subscriber("Subscriber-2");

        // Create subscriber threads
        Thread subscriber1Thread = new Thread(() -> {
            subscriber1.subscribe("topic1");
            
            // Keep subscriber alive
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Subscriber-1-Thread");

        Thread subscriber2Thread = new Thread(() -> {
            subscriber2.subscribe("topic1");
            
            // Keep subscriber alive
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Subscriber-2-Thread");

        // Create publisher thread
        Thread publisherThread = new Thread(() -> {
            // Give time for subscribers to connect
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Publish messages
            for (int i = 0; i < 5; i++) {
                publisher.publishMessage("topic1", "Message " + i + " from Publisher");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }, "Publisher-Thread");

        // Start all threads
        subscriber1Thread.start();
        subscriber2Thread.start();
        publisherThread.start();

        // Let the program run for a while
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Clean up
        subscriber1Thread.interrupt();
        subscriber2Thread.interrupt();
        publisherThread.interrupt();
    }
} 