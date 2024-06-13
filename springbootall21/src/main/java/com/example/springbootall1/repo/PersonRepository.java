package com.example.springbootall1.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootall1.pojo.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}
