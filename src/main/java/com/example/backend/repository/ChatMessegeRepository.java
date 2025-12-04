package com.example.backend.repository;

import com.example.backend.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessegeRepository extends JpaRepository<ChatMessage, Long> {
}
