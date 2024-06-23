package com.example.demo.controller;

import com.example.demo.entity.Formation;
import com.example.demo.service.FormationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FormationController {

    @Autowired
    FormationService formationService ;

    @GetMapping("/getAllFormation")
    public ResponseEntity<Iterable<Formation>> getAllFormation() {

        Iterable<Formation> formations = formationService.getAllFormation();

        return new ResponseEntity<>(formations, HttpStatus.OK);



    }

    @GetMapping("/getFormationsforEnrolledStudent/{student_id}") // Add path variable here
    public ResponseEntity<Iterable<Formation>> getFormationsforEnrolledStudent(@PathVariable("student_id") Long student_id) {
        Iterable<Formation> formations = formationService.getFormationsforEnrolledStudent(student_id);
        return new ResponseEntity<>(formations, HttpStatus.OK);
    }

    @GetMapping("/getFormationsForFormateur/{formateur_id}")
    public ResponseEntity<Iterable<Formation> >getFormationsForFormateur(@PathVariable("formateur_id")Long formateur_id ) {

        Iterable<Formation>formations=formationService.getFormationsForFormateur(formateur_id);
        return new ResponseEntity<>(formations,HttpStatus.OK) ;

    }



    @GetMapping("/getFormationById/{id}")
    public ResponseEntity<Formation> getFormationById(@PathVariable("id") Long id) {

        Formation formation = formationService.getFormationById(id);
        System.out.println("formation  : " + formation);
        return new ResponseEntity<>(formation, HttpStatus.OK);

    }

    @PostMapping({"/createNewFormation"})
    public ResponseEntity<Formation> createNewFormation(@RequestBody Formation formation) {


        return new ResponseEntity<>(formationService.createNewFormation(formation), HttpStatus.OK);



    }

    @GetMapping("/getFormationByNom/{nom}")
    public ResponseEntity<Formation> getFormationByNom(@PathVariable("nom") String nom) {

        Formation formation = formationService.getFormationbyNom(nom);
        System.out.println("formation  : " + formation);
        return new ResponseEntity<>(formation, HttpStatus.OK);

    }


    @DeleteMapping("/rem_formation/{id}")

    public ResponseEntity<?> rem_formation(@PathVariable("id")  Long id ){

        formationService.rem_Formation(id);
        return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/update_formation")
    public  ResponseEntity<Formation> update_formation(@RequestBody Optional<Formation> optionalFormation){

        Formation formation =optionalFormation.orElseThrow(() -> new IllegalArgumentException("Invalid formation  for update"));

        return new ResponseEntity<>(formationService.update_formation(formation),HttpStatus.OK);

    }




}
