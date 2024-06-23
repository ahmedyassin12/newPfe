package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Person;

import java.util.Optional;

@Repository
public interface PersonDAO extends JpaRepository<Person, Long> {

    Optional<Person>  findUserByEmail(String email) ;


    Optional<Person> findByUsername(String username);


}