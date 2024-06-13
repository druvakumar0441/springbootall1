package com.example.springbootall1.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springbootall1.pojo.Person;
import com.example.springbootall1.repo.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person updatePerson(Integer id, Person updatedPerson) {
        return personRepository.findById(id)
            .map(existingPerson -> {
                updatedPerson.setId(id);
                return personRepository.save(updatedPerson);
            })
            .orElseThrow(() -> new RuntimeException("Person not found with id: " + id));
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
