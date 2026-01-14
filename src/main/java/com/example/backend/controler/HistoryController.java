package com.example.backend.controler;

import com.example.backend.model.ChatMessage;
import com.example.backend.repository.ChatMessegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class HistoryController {

    @Autowired
    private ChatMessegeRepository chatMessageRepository;

    @GetMapping("/history")
    public List<ChatMessage> getHistory(@RequestParam String user1, @RequestParam String user2) {
        System.out.println("Pobieranie historii dla: " + user1 + " i " + user2);
        return chatMessageRepository.findConversationHistory(user1, user2);
    }
}