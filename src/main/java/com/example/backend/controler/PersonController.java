package com.example.backend.controler;

import com.example.backend.model.Person;
import com.example.backend.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Person person){
        return personService.save(person);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Person person){
        return personService.login(person);
    }


    @GetMapping("/getPublicKey/{username}")
    public ResponseEntity<?> getPublicKey(@PathVariable String username) {
        return personService.getPublicKeyANDcreateConwesation(username);
    }
}