package com.example.springbootall1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootall1.pojo.Person;
import com.example.springbootall1.pojo.UserCredentials;
import com.example.springbootall1.service.UserCredentialsService;

@RestController
@RequestMapping("/api/usercredentials")
public class UserCredentialsController {

    @Autowired
    private UserCredentialsService userCredentialsService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UserCredentials>> getPersonById(@PathVariable Integer id) {
        Optional<Optional<UserCredentials>> optionalPerson = Optional.ofNullable(Optional.ofNullable(userCredentialsService.getUserCredentialsById(id)));
        return optionalPerson.map(person -> ResponseEntity.ok().body(person))
                             .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserCredentials>> getAllUserCredentials() {
        List<UserCredentials> userCredentialsList = userCredentialsService.getAllUserCredentials();
        return ResponseEntity.ok().body(userCredentialsList);
    }
    
    @PostMapping
    public ResponseEntity<UserCredentials> createUserCredentials(@RequestBody UserCredentials userCredentials) {
        UserCredentials savedUserCredentials = userCredentialsService.createUserCredentials(userCredentials);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserCredentials);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserCredentials> updateUserCredentials(@PathVariable Integer id, @RequestBody UserCredentials updatedUserCredentials) {
        try {
            return ResponseEntity.ok(userCredentialsService.updateUserCredentials(id, updatedUserCredentials));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserCredentials(@PathVariable Integer id) {
        userCredentialsService.deleteUserCredentials(id);
        return ResponseEntity.noContent().build();
    }
}
