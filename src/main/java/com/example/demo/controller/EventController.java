package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v5/event")

public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping("/getAllEvents")
    public Iterable<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    @GetMapping("/getEventforEnrolledStudent/{studentId}")
    public ResponseEntity<Iterable<Event>> getEventforEnrolledStudent(@PathVariable("studentId") Long studentId) {
        try {
            Iterable<Event> events = eventService.getEventForEnrolledStudent(studentId);
            return ResponseEntity.ok(events);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/getEventById/{id}")
    public Event getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/getEventByName/{name}")
    public Event getEventByName(@PathVariable String name) {
        return eventService.getEventByName(name);
    }

    @PostMapping("/createNewEvent")
    public Event createNewEvent(@RequestBody Event event) {





        return eventService.createNewEvent(event);
    }

    @DeleteMapping("/rem_event/{id}")
    public void rem_event(@PathVariable Long id) {
        eventService.rem_event(id);
    }

    @PutMapping("/update_event")
    public Event update_event(@RequestBody Event event) {

        return eventService.update_event(event);
    }

}