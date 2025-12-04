package com.example.backend.service;


import com.example.backend.repository.ChatMessegeRepository;
import org.springframework.stereotype.Service;

@Service
public class ChatMessegeService {

    ChatMessegeRepository chatMessegeRepository;

    public ChatMessegeService(ChatMessegeRepository chatMessegeRepository) {
        this.chatMessegeRepository = chatMessegeRepository;
    }
}
