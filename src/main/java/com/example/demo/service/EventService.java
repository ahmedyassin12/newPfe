package com.example.demo.service;

import com.example.demo.dao.EventDAO;
import com.example.demo.dao.EventEnrollementDAO;
import com.example.demo.entity.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EventService {

    @Autowired
    private EventDAO eventDAO;


    @Autowired
    private EventEnrollementDAO eventEnrollementDAO;


    public Iterable<Event> getAllEvents() {
        return eventDAO.findAll();
    }



    public Iterable<Event> getEventForEnrolledStudent(Long studentId) {
        return eventDAO.getEventForEnrolledStudent(studentId);
    }

    public Event getEventById(Long id) {
        Optional<Event> optional = eventDAO.findById(id);
        Event event;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException("Event not found for id :: " + id);
        }
        return event;
    }

    public Event getEventByName(String name) {
        Optional<Event> optional = eventDAO.findBynom(name);
        Event event;
        if (optional.isPresent()) {
            event = optional.get();
        } else {
            throw new RuntimeException("Event not found for name :: " + name);
        }
        return event;
    }

    public Event createNewEvent(Event event) {

        eventDAO.save(event);
        log.info("Event {} is saved", event.getEvent_id());
        return event;
    }


    public void rem_event(Long id) {

       Event event= eventDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));



        eventDAO.deleteById(id);
    }

    public Event update_event(Event event) {


        return eventDAO.save(event);
    }
}