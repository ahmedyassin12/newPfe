package com.example.demo.service;

import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.ChangePasswordRequest;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.dao.PersonDAO;
import com.example.demo.entity.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static com.example.demo.entity.Role.MANAGER;

@Slf4j
@Service
@RequiredArgsConstructor

public class PersonService {

    @Autowired
   private PersonDAO personDAO ;

    private final PasswordEncoder passwordEncoder ;

    @Autowired
    private AuthenticationService authenticationService ;
    @Transactional()
    public Iterable<Person> getAllUsers(){


        return personDAO.findAll();


    }

    public Person findUserByEmail(String Email  ){

        Optional<Person> optional=personDAO.findUserByEmail(Email) ;

        Person person ;
        if(optional.isPresent()){

            person = optional.get();

        }

        else {

            throw new RuntimeException("User not found for email  ::  "+Email )  ;

        }

        return person ;


    }

    public Person getUserByUsername(String username  ){

        Optional<Person> optional=personDAO.findByUsername(username) ;

        Person person ;
        if(optional.isPresent()){

            person = optional.get();

        }

        else {

            throw new RuntimeException("User not found for username  ::  "+username )  ;

        }

        return person ;


    }
    public Person getUserbyId(Long id ){
        Optional<Person> optional=personDAO.findById(id) ;

        Person user;
        if(optional.isPresent()) user=optional.get();
        else {

            throw new RuntimeException("Student not found for id  ::  "+id  )  ;


        }

        return user ;



    }

    public Person createNewUser(Person user ){


        user.setPassword(passwordEncoder.encode(user.getPassword()));
        personDAO.save(user);
        log.info("user {} is saved", user.getId());

        return user;


    }
    public void rem_user(long id ){

        personDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("user not found with id: " + id));

        personDAO.deleteById(id);


    }
    public Person update_user(Person user){

        if (user==null||user.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid student ID for update");
        }
        return personDAO.save(user) ;



    }


    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {


        var user = (Person) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal() ;


                //check if current pass is correct :
                if( !passwordEncoder.matches(request.getCurrentPassword(),user.getPassword() ) ){

                    throw new IllegalArgumentException("wrong password") ;

                }


                //check if password are the same :
                if (! request.getConfirmationPassword().equals(request.getNewPassword()))
                    throw new IllegalArgumentException("password are not the same") ;


                //update password
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));

                //save pass
                personDAO.save(user) ;

    }
}
