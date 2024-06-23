package com.example.demo.dao;

import com.example.demo.entity.EventEnrollement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventEnrollementDAO extends CrudRepository<EventEnrollement,Long> {



    @Query("SELECT e FROM EventEnrollement e WHERE e.student.id = :studentId")
    Iterable<EventEnrollement> getEventEnrollementForStudent(Long studentId);


    @Query("SELECT e FROM EventEnrollement e " +
            "JOIN e.event f " +
            "WHERE f.id = :event_id")
    Iterable<EventEnrollement> getEventEnrollement(@Param("event_id") Long event_id);





}

