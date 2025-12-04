package com.example.backend.controler;

import com.example.backend.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicKeyControler {

    private  final PersonService personService;

    public PublicKeyControler(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getPublicKey/{username}")
    public ResponseEntity<?> getPublicKey(@RequestParam String username) {
        return personService.getPublicKey(username);

    }
}
