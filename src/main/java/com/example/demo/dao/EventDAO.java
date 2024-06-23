package com.example.demo.dao;

import com.example.demo.entity.Event;
import com.example.demo.entity.Formation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventDAO extends CrudRepository<Event,Long> {

    Optional<Event> findBynom(String nom);
    Optional<Event> findByDescription(String description);
    Optional<Event> findByLocation(String location);
    Optional<Event> findBydate(Date date);



    @Query("SELECT e FROM Event e JOIN e.enrollements ee WHERE ee.student.id = :studentId")
    Iterable<Event> getEventForEnrolledStudent(@Param("studentId") Long studentId);


}
