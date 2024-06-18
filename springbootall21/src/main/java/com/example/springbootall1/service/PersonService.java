package com.example.springbootall1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootall1.exception.ResourceNotFoundException;
import com.example.springbootall1.pojo.Person;
import com.example.springbootall1.pojo.UserCredentials;
import com.example.springbootall1.repo.PersonRepository;
import com.example.springbootall1.repo.UserCredentialsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    
    @Autowired
    private UserCredentialsService userCredentialsService;


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Integer id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found with id: " + id));
    }

    public Person createPerson(Person person) {
    	 if (person.getUserCredentials() != null) {
             // Save UserCredentials first to ensure it has an assigned UID
             UserCredentials savedUserCredentials = userCredentialsService.createUserCredentials(person.getUserCredentials());
             // Set the saved UserCredentials in Person
             person.setUserCredentials(savedUserCredentials);
         }
         // Save Person
         return personRepository.save(person);
    }

    public Person updatePerson(Integer id, Person updatedPerson) {
        return personRepository.findById(id)
            .map(existingPerson -> {
                updatedPerson.setPid(id);
                return personRepository.save(updatedPerson);
            })
            .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
