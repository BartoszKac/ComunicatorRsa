package com.example.backend.controler;


import com.example.backend.model.Person;
import com.example.backend.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonCotroler {

    private final PersonService personService;

    public PersonCotroler(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Person person){
        return personService.save(person);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Person person){
        return null;
    }


}
