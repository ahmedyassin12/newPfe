package com.example.demo.dao;

import com.example.demo.entity.Manager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface ManagerDAO extends CrudRepository<Manager,Long> {

    Optional<Manager> findManagerByEmail(String Email);

    Optional<Manager> findManagerBynom(String nom);


    Optional<Manager> findManagerByUsername(String username);

}
