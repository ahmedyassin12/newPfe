package com.example.demo.controller;

import com.example.demo.auth.ChangePasswordRequest;
import com.example.demo.service.PersonService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.Person;

import java.security.Principal;
import java.text.ParseException;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class PersonController {


    @Autowired
    private PersonService userService;


    @GetMapping("/getAllUsers")
    public ResponseEntity<Iterable<Person>> getAllUsers() {

        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);

    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserbyId(id), HttpStatus.OK);
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<Person> findUserByEmail(@PathVariable String email) {

        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);

    }
    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<Person> getUserByUsername(@PathVariable String username) {

        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);


    }


    @PostMapping ("/createNewUser")
    public ResponseEntity<Person> createNewUser(@RequestBody Person user) {

        user.getPassword();

        return new ResponseEntity<>(userService.createNewUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<Person> updateUser(@RequestBody Optional<Person>  updatedUser ) {
        Person user =updatedUser.orElseThrow(() -> new IllegalArgumentException("Invalid Student Email for update"));


        return new ResponseEntity<>(userService.update_user(user), HttpStatus.OK);



    }



    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.rem_user(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PatchMapping("/changePassword")
    public ResponseEntity<?>changePaassword(
            @RequestBody ChangePasswordRequest request
            ,
            Principal connectedUser
    )
    {


        userService.changePassword(request,connectedUser) ;

        return  ResponseEntity.ok().build() ;

    }


}