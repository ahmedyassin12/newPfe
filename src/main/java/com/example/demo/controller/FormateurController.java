package com.example.demo.controller;

import com.example.demo.entity.Formateur;
import com.example.demo.service.TrainerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class FormateurController {



        @Autowired
        TrainerService formateurService ;


        @RequestMapping("/testFormateur")
        public String test() {
            return ("Je fonctionne correctement");
        }



        @PostConstruct
        public void initFormateur () throws ParseException {



            formateurService.initFormateur();



        }



        @GetMapping("/getAllFormateurs")
        public ResponseEntity<Iterable<Formateur>> getAllFormateurs() {

            Iterable<Formateur> formateurs = formateurService.getallFormateur();

            return new ResponseEntity<>(formateurs, HttpStatus.OK);



        }
        @GetMapping("/getFormateurByid/{id}")
        public ResponseEntity<Formateur> getFormateurByid(@PathVariable("id") int id) {

            Formateur formateur = formateurService.getFormateurById(id);
            return new ResponseEntity<>(formateur, HttpStatus.OK);

        }
    @GetMapping("/getFormateurByUsername/{username}")
    public ResponseEntity<Formateur> getFormateurByUsername(@PathVariable("username") String username) {

        Formateur formateur = formateurService.getFormateurByUsername(username);
        return new ResponseEntity<>(formateur, HttpStatus.OK);

    }


        @PostMapping({"/createNewFormateur"})
        public ResponseEntity<Formateur> createNewLivre(@RequestBody Formateur formateur) {


            return new ResponseEntity<>(formateurService.createNewFormateur(formateur), HttpStatus.OK);



        }

        @GetMapping("/getFormateurByNom/{nom}")
        public ResponseEntity<Formateur> getFormateurByNom(@PathVariable("nom") String nom) {

            Formateur formateur = formateurService.getFormateurByNom(nom);
            return new ResponseEntity<>(formateur, HttpStatus.OK);

        }
    @GetMapping("/getFormateurByEmail/{Email}")
    public ResponseEntity<Formateur> getFormateurByEmail(@PathVariable("Email") String Email) {

        Formateur formateur = formateurService.getFormateurByEmail(Email);
        System.out.println("formateur : " + formateur);
        return new ResponseEntity<>(formateur, HttpStatus.OK);

    }

        @DeleteMapping("/rem_formateur/{id}")

        public ResponseEntity<?> rem_formateur(@PathVariable("id")  int id ){

            formateurService.rem_formateur(id);
            return new ResponseEntity<>(HttpStatus.OK) ;


        }

        @PutMapping("/update_formateur")
        public  ResponseEntity<Formateur> update_formateur(@RequestBody Optional<Formateur> optionalFormateur){

            Formateur formateur =optionalFormateur.orElseThrow(() -> new IllegalArgumentException("Invalid formateur Email for update"));

            return new ResponseEntity<>(formateurService.update_formateur(formateur),HttpStatus.OK);

        }







}
