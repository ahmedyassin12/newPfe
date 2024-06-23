package com.example.demo.controller;
import com.example.demo.entity.Paiement;
import com.example.demo.service.EnrollementService;
import com.example.demo.service.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
    @RequestMapping("/api/v9/Paiement")

public class PaiementController {



    @Autowired
   private  PaiementService paiementService ;

    @Autowired
    private EnrollementService enrollementService;

    @GetMapping("/getAllPaiements")
    public ResponseEntity<Iterable<Paiement>> getAllPaiements() {

        Iterable<Paiement> paiements = paiementService.getAllPaiements() ;

        return new ResponseEntity<>(paiements, HttpStatus.OK);



    }
    @GetMapping("/getPaiementsOfEnrollement/{enrollementId}")

    public ResponseEntity<Iterable<Paiement> >getPaiementsOfEnrollement( @PathVariable Long enrollementId){


return new ResponseEntity<>(paiementService.getPaiementsOfEnrollement(enrollementId),HttpStatus.OK) ;


    }


    @GetMapping("/getPaiementByStudent_id/{student_id}")
    public ResponseEntity<List<Paiement>> getPaiementByStudent_id(@PathVariable("student_id") int student_id ) {

        List<Paiement> paiements = paiementService.getPaiementByStudent_id(student_id);

        return new ResponseEntity<>(paiements, HttpStatus.OK);

    }

    @GetMapping("/getPaiementById/{id}")
    public ResponseEntity<Paiement> getPaiementById(@PathVariable("id") long id) {

        Paiement paiement = paiementService.getpaiementById(id);
        return new ResponseEntity<>(paiement, HttpStatus.OK);

    }

    @PostMapping({"/createPaiement"})
    public ResponseEntity<Paiement> createPaiement(@RequestBody Paiement paiement) {





        return new ResponseEntity<>(paiementService.createPaiement(paiement), HttpStatus.OK);



    }
    @DeleteMapping("/deletePaiement/{id}")

    public ResponseEntity<?> rem_student(@PathVariable("id")  int id ){

        paiementService.deletePaiement(id);
        return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/updatePaiement")
    public  ResponseEntity<Paiement> updatePaiement(@RequestBody Optional<Paiement> optionalPaiment){

        Paiement paiement =optionalPaiment.orElseThrow(() -> new IllegalArgumentException("Invalid paiment id for update"));

        return new ResponseEntity<>(paiementService.updatePaiement(paiement),HttpStatus.OK);

    }













}
