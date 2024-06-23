package com.example.demo.controller;

import com.example.demo.entity.Enrollement;
import com.example.demo.service.EnrollementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController

public class EnrollementController {

    @Autowired
    private EnrollementService enrollementService;

    @GetMapping("/getAllEnrollements")
    public Iterable<Enrollement> getAllEnrollements() {

        return enrollementService.getAllEnrollements();

    }
    @GetMapping("/getEnrollementsForStudent/{studentId}")
    public Iterable<Enrollement> getEnrollementsForStudent(@PathVariable Long studentId) {


        return enrollementService.getEnrollementForStudent(studentId);


    }

    @GetMapping("/getFormationEnrollement/{formation_id}")

    public   Iterable<Enrollement> getFormationEnrollement(@PathVariable Long formation_id){


        return enrollementService.getFormationEnrollement(formation_id);

    }

    @GetMapping("/getEnrollementById/{id}")
    public Enrollement getEnrollementById(@PathVariable Long id) {
        return enrollementService.getEnrollementById(id);
    }

    @PostMapping("/createNewEnrollement")
    public Enrollement createNewEnrollement(@RequestBody Enrollement enrollement) {

        return enrollementService.createNewEnrollement(enrollement);

    }

    @DeleteMapping("/removeEnrollement/{id}")
    public void removeEnrollement(@PathVariable Long id) {
        enrollementService.removeEnrollement(id);
    }


    @PutMapping("/updateEnrollement")
    public Enrollement updateEnrollement(@RequestBody Enrollement enrollement) {

        return enrollementService.updateEnrollement(enrollement);

    }


}