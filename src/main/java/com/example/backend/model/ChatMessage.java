package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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