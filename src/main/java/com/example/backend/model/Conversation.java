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
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    private Long IdPerson1;
    private Long IdPerson2;

    public Conversation() {
    }
    public Conversation(Long id, Long idPerson1, Long idPerson2) {
        this.id = id;
        IdPerson1 = idPerson1;
        IdPerson2 = idPerson2;
<<<<<<< HEAD
=======
=======

    private Long idPerson1;
    private Long idPerson2;

    public Conversation() {
    }


    public Conversation(Long id, Long idPerson1, Long idPerson2) {
        this.id = id;
        this.idPerson1 = idPerson1;
        this.idPerson2 = idPerson2;
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPerson1() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
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
<<<<<<< HEAD
=======
=======
        return idPerson1;
    }

    public void setIdPerson1(Long idPerson1) {
        this.idPerson1 = idPerson1;
    }

    public Long getIdPerson2() {
        return idPerson2;
    }

    public void setIdPerson2(Long idPerson2) {
        this.idPerson2 = idPerson2;
    }
}
>>>>>>> 416c793 (Odświeżenie wyglądu UI i poprawa logiki czatu)
>>>>>>> f901a3d (Odświeżenie wyglądu UI i poprawa logiki czatu)
