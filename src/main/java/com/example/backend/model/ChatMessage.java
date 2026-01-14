package com.example.backend.model;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< HEAD
=======
=======
import jakarta.persistence.*;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    private Long conversationId;
    private String from;
    private String text;

    public ChatMessage() {
    }

    public ChatMessage(Long id, Long conversationId, String from, String text) {
        this.id = id;
        this.conversationId = conversationId;
        this.from = from;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getConversationId() {
        return conversationId;
    }

    public void setConversationId(Long conversationId) {
        this.conversationId = conversationId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
<<<<<<< HEAD
=======
=======

    private String sender;    // nadawca (odpowiada "from" na froncie)
    private String recipient; // odbiorca (odpowiada "to" na froncie)

    @Column(columnDefinition = "TEXT")
    private String text;

    private Long conversationId;

    public ChatMessage() {}

    public ChatMessage(String sender, String recipient, String text) {
        this.sender = sender;
        this.recipient = recipient;
        this.text = text;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSender() { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public Long getConversationId() { return conversationId; }
    public void setConversationId(Long conversationId) { this.conversationId = conversationId; }
}
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
