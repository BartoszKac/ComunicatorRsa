package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long IdPerson1;
    private Long IdPerson2;

    public Conversation() {
    }
    public Conversation(Long id, Long idPerson1, Long idPerson2) {
        this.id = id;
        IdPerson1 = idPerson1;
        IdPerson2 = idPerson2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPerson1() {
        return IdPerson1;
    }

    public void setIdPerson1(Long idPerson1) {
        IdPerson1 = idPerson1;
    }

    public Long getIdPerson2() {
        return IdPerson2;
    }

    public void setIdPerson2(Long idPerson2) {
        IdPerson2 = idPerson2;
    }
}
