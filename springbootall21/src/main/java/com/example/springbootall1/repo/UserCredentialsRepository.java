package com.example.springbootall1.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootall1.pojo.UserCredentials;


@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Integer>{


}
