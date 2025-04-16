package com.pubsub.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Message {
    public final String message;
    private final String  messageId;
    private final long createdAt;

    public Message(String message) {
        this.message = message;
        this.messageId = UUID.randomUUID().toString();
        this.createdAt = System.currentTimeMillis();
    }

    public Map<String, Object> getMessage() {
        Map<String, Object> messageDetails = new HashMap<>();
        messageDetails.put("message", message);
        messageDetails.put("messageId", messageId);
        messageDetails.put("createdAt", createdAt);
        return messageDetails;
    }

    public String getMessageId() {
        return messageId;
    }
}
