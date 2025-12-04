package com.example.backend.service;

import com.example.backend.model.Person;
import com.example.backend.repository.PersonRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

   private final PersonRepository personRespository;

    public PersonService(PersonRepository personRespository) {
        this.personRespository = personRespository;
    }

    public ResponseEntity<?> getPublicKeyANDcreateConwesation(String name){
        if(personRespository.findByName(name).isPresent()){
            String publicKey = personRespository.findByName(name).get().getPublic_key();
            return ResponseEntity.ok(publicKey);
        }else {
            return ResponseEntity.badRequest().body("User with this name does not exist");
        }

    }

    public ResponseEntity<?> getAllPersons(){
        return ResponseEntity.ok(personRespository.findAll());
    }

    public ResponseEntity<?> save(Person person){
        System.out.println(person.getName());
        System.out.println(person.getPassword());
        System.out.println(
                person.getPublic_key()
        );
        if(personRespository.findByName(person.getName()).isPresent()){
            return ResponseEntity.badRequest().body("User with this name already exists");
        } else {
            personRespository.save(person);
            return ResponseEntity.ok("User registered successfully");
        }

    }

    public ResponseEntity<?> login(Person person){
        if(personRespository.findByName(person.getName()).isPresent()){
            Person foundPerson = personRespository.findByName(person.getName()).get();
            if(foundPerson.getPassword().equals(person.getPassword())){
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.badRequest().body("Incorrect password");
            }
        } else {
            return ResponseEntity.badRequest().body("User with this name does not exist");
        }
    }
}
