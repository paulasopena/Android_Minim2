package edu.upc.dsa.andoroid_dsa.models;

import edu.upc.dsa.andoroid_dsa.activities.ChatActivity;

public class ChatMessage {
    String name;
    String message;
    public ChatMessage(){}

    public ChatMessage(String name, String message) {
        this.name = name;
        this.message = message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
