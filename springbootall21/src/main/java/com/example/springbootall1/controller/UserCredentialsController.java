package com.example.springbootall1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.springbootall1.pojo.UserCredentials;
import com.example.springbootall1.service.UserCredentialsService;

@RestController
@RequestMapping("/api/usercredentials")
public class UserCredentialsController {

    @Autowired
    private UserCredentialsService userCredentialsService;

    @GetMapping("/{id}")
    public ResponseEntity<UserCredentials> getUserCredentialsById(@PathVariable Integer id) {
        return userCredentialsService.getUserCredentialsById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserCredentials createUserCredentials(@RequestBody UserCredentials userCredentials) {
        return userCredentialsService.createUserCredentials(userCredentials);
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
