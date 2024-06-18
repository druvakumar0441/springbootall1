package com.example.springbootall1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootall1.exception.ResourceNotFoundException;
import com.example.springbootall1.pojo.UserCredentials;
import com.example.springbootall1.repo.UserCredentialsRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import java.util.List;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;
    
    @Autowired
    private Validator validator;

    public UserCredentials getUserCredentialsById(Integer id) {
        return userCredentialsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserCredentials not found with id: " + id));
    }
    
    public List<UserCredentials> getAllUserCredentials() {
        return userCredentialsRepository.findAll();
    }
    @Transactional
    public UserCredentials createUserCredentials(UserCredentials userCredentials) {
        // Validate UserCredentials before saving
        validateUserCredentials(userCredentials);

        // Save UserCredentials
        return userCredentialsRepository.save(userCredentials);
    }
    public UserCredentials updateUserCredentials(Integer id, UserCredentials updatedUserCredentials) {
        return userCredentialsRepository.findById(id)
            .map(existingUserCredentials -> {
                updatedUserCredentials.setUid(id);
                return userCredentialsRepository.save(updatedUserCredentials);
            })
            .orElseThrow(() -> new RuntimeException("UserCredentials not found with id: " + id));
    }

    public void deleteUserCredentials(Integer id) {
        userCredentialsRepository.deleteById(id);
    }
    
    private void validateUserCredentials(UserCredentials userCredentials) {
        // Validate using Hibernate Validator
        var violations = validator.validate(userCredentials);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Validation failed for UserCredentials", violations);
        }
    }
}
