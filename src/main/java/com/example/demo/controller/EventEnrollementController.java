package com.example.demo.controller;

import com.example.demo.entity.EventEnrollement;
import com.example.demo.service.EventEnrollementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventEnrollementController {

    @Autowired
    private EventEnrollementService enrollementService;

    @GetMapping("/getAllEventEnrollements")
    public Iterable<EventEnrollement> getAllEventEnrollements() {
        return enrollementService.getAllEnrollements();
    }

    @GetMapping("/getEventEnrollementForStudent/{studentId}")
    public Iterable<EventEnrollement> getEventEnrollementForStudent(@PathVariable("studentId") Long studentId) {
        return enrollementService.getEventEnrollementForStudent(studentId);
    }


    //get Enrollement of that specific event 
    @GetMapping("/getEventEnrollementForEvent/{event_id}")
    public Iterable<EventEnrollement> getEventEnrollementForEvent(@PathVariable("event_id")Long event_id){

        return enrollementService.getEventEnrollement(event_id);

    }


    @GetMapping("/getEventEnrollementById/{id}")
    public EventEnrollement getEventEnrollementById(@PathVariable("id") Long id) {
        return enrollementService.getEnrollementById(id);
    }

    @PostMapping("/createNewEventEnrollement")
    public EventEnrollement createNewEventEnrollement(@RequestBody EventEnrollement enrollement) {
        return enrollementService.createNewEnrollement(enrollement);
    }

    @DeleteMapping("/removeEventEnrollement/{id}")
    public void removeEventEnrollement(@PathVariable("id") Long id) {
        enrollementService.removeEnrollement(id);
    }

    @PutMapping("/updateEventEnrollement")
    public EventEnrollement updateEventEnrollement(@RequestBody EventEnrollement enrollement) {
        return enrollementService.updateEnrollement(enrollement);
    }
}
