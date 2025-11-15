package com.example.backend.controler;


import com.example.backend.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/send") // klient wysyła na /app/send
    @SendTo("/topic/messages") // serwer rozsyła do wszystkich subskrybentów
    public ChatMessage send(ChatMessage message) {
        return message; // prosto odsyła do wszystkich
    }
}
