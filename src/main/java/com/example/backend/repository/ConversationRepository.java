package com.example.backend.repository;

import com.example.backend.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {


    boolean existsByIdPerson1OrIdPerson2(Long idPerson1, Long idPerson2);


}
