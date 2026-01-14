package com.example.backend.controler;

import com.example.backend.model.ChatMessage;
import com.example.backend.repository.ChatMessegeRepository; // Załóżmy, że masz takie repo
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ChatController {

    @Autowired
    private ChatMessegeRepository chatMessageRepository;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public ChatMessage send(ChatMessage message) {

        if (message.getSender() != null && message.getRecipient() != null) {
            chatMessageRepository.save(message);
        }
        return message;
    }
}