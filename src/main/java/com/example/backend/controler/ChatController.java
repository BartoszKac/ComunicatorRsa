package com.example.backend.controler;

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)

import com.example.backend.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
<<<<<<< HEAD
=======
=======
import com.example.backend.model.ChatMessage;
import com.example.backend.repository.ChatMessegeRepository; // Załóżmy, że masz takie repo
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)

@Controller
public class ChatController {

<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    @MessageMapping("/send") // klient wysyła na /app/send
    @SendTo("/topic/messages") // serwer rozsyła do wszystkich subskrybentów
    public ChatMessage send(ChatMessage message) {
        return message; // prosto odsyła do wszystkich
    }
}
<<<<<<< HEAD
=======
=======
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
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
