package com.example.backend.service;



import com.example.backend.repository.ConversationRepository;
import com.example.backend.repository.PersonRepository;

import org.springframework.stereotype.Service;

@Service
public class ConversationService {


    ConversationRepository conversationRepository;
    PersonRepository personRepository;

    public ConversationService(ConversationRepository conversationRepository, PersonRepository personRepository) {
        this.conversationRepository = conversationRepository;
        this.personRepository = personRepository;
    }

    public boolean ConversationExists(Long id, Long id2) {
        return conversationRepository.existsByIdPerson1OrIdPerson2(id, id2);
    }


}
