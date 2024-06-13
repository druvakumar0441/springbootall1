package com.example.springbootall1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootall1.pojo.UserCredentials;
import com.example.springbootall1.repo.UserCredentialsRepository;

import java.util.Optional;

@Service
public class UserCredentialsService {

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    public Optional<UserCredentials> getUserCredentialsById(Integer id) {
        return userCredentialsRepository.findById(id);
    }

    public UserCredentials createUserCredentials(UserCredentials userCredentials) {
        return userCredentialsRepository.save(userCredentials);
    }

    public UserCredentials updateUserCredentials(Integer id, UserCredentials updatedUserCredentials) {
        return userCredentialsRepository.findById(id)
            .map(existingUserCredentials -> {
                updatedUserCredentials.setId(id);
                return userCredentialsRepository.save(updatedUserCredentials);
            })
            .orElseThrow(() -> new RuntimeException("UserCredentials not found with id: " + id));
    }

    public void deleteUserCredentials(Integer id) {
        userCredentialsRepository.deleteById(id);
    }
}
