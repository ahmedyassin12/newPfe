package com.example.demo.controller;


import com.example.demo.entity.Manager;
import com.example.demo.service.ManagerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Optional;

@RestController
public class ManagerController {

    @Autowired
    ManagerService managerService;



    @PostConstruct
    public void initManagers() throws ParseException {
        managerService.initManager();

    }
    @GetMapping("/getAllManagers")
    public ResponseEntity<Iterable<Manager>> getAllManagers() {
        Iterable<Manager> managers = managerService.getAllManagers();
        return new ResponseEntity<>(managers, HttpStatus.OK);
    }

    @GetMapping("/getManagerById/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable("id") long id) {
        Manager manager = managerService.getManagerById(id);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @PostMapping("/createNewManager")
    public ResponseEntity<Manager> createNewManager(@RequestBody Manager manager) {
        return new ResponseEntity<>(managerService.createNewManager(manager), HttpStatus.OK);
    }

    @GetMapping("/getManagerByName/{name}")
    public ResponseEntity<Optional<Manager>> getManagerByName(@PathVariable("name") String name) {
        Optional<Manager> manager = managerService.getManagerBynom(name);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @GetMapping("/getManagerByEmail/{Email}")
    public ResponseEntity<Optional<Manager>> getManagerByEmail(@PathVariable("Email") String Email) {
        Optional<Manager> manager = managerService.getManagerByEmail(Email);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }
    @GetMapping("/getManagerByUsername/{username}")
    public ResponseEntity<Optional<Manager>> getManagerByUsername(@PathVariable("username") String username) {
        Optional<Manager> manager = managerService.getManagerByUsername(username);
        return new ResponseEntity<>(manager, HttpStatus.OK);
    }

    @DeleteMapping("/rem_manager/{id}")
    public ResponseEntity<?> rem_manager(@PathVariable("id") long id) {
        managerService.rem_manager(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update_manager")
    public ResponseEntity<Manager> update_manager(@RequestBody Optional<Manager> optionalManager) {
        Manager manager = optionalManager.orElseThrow(() -> new IllegalArgumentException("Invalid Manager Email for update"));
        return new ResponseEntity<>(managerService.update_manager(manager), HttpStatus.OK);
    }

}