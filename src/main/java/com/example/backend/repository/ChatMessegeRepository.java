package com.example.backend.repository;

import com.example.backend.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface ChatMessegeRepository extends JpaRepository<ChatMessage, Long> {
}
=======
<<<<<<< HEAD

public interface ChatMessegeRepository extends JpaRepository<ChatMessage, Long> {
}
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ChatMessegeRepository extends JpaRepository<ChatMessage, Long> {


    @Query("SELECT m FROM ChatMessage m WHERE " +
            "(m.sender = :user1 AND m.recipient = :user2) OR " +
            "(m.sender = :user2 AND m.recipient = :user1) " +
            "ORDER BY m.id ASC")
    List<ChatMessage> findConversationHistory(@Param("user1") String user1, @Param("user2") String user2);
}
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
