package com.example.demo.dao;

import com.example.demo.entity.Enrollement;
import com.example.demo.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface PaiementDAO extends JpaRepository<Paiement, Long> {

/*
    List<Paiement> findByEnrollement_EnrollementId(Long enrollementId);*/


    @Query("SELECT p " +
            "FROM Paiement p " +
            "JOIN p.enrollement e " +
            "WHERE e.student.id = :studentId")
    List<Paiement> findByStudentId(@Param("studentId") long studentId);


    @Query("SELECT p FROM Paiement p WHERE p.enrollement.id = :enrollementId")
    Iterable<Paiement> getPaiementsOfEnrollement(@Param("enrollementId") Long enrollementId);


}

