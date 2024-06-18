package com.example.springbootall1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootall1.pojo.Person;
import com.example.springbootall1.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Integer id) {
        Optional<Person> optionalPerson = Optional.ofNullable(personService.getPersonById(id));
        return optionalPerson.map(person -> ResponseEntity.ok().body(person))
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        // Set the Person instance in UserCredentials
        if (person.getUserCredentials() != null) {
            person.getUserCredentials().setPerson(person);
        }
        Person savedPerson = personService.createPerson(person);
        return ResponseEntity.ok().body(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person updatedPerson) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, updatedPerson));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Integer id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
