package com.example.demo.service;
import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.dao.FormateurDAO;
import com.example.demo.entity.Formateur;
import com.example.demo.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {



    @Autowired
    private final PasswordEncoder passwordEncoder ;

        @Autowired
        private FormateurDAO formateurDAO ;

        @Autowired
        private AuthenticationService authenticationService ;


        public Iterable<Formateur> getallFormateur(){


            return  this.formateurDAO.findAll();


        }


        public Formateur getFormateurByEmail(String Email  ){

            Optional<Formateur> optional=formateurDAO.findFormateurByEmail(Email) ;

            Formateur formateur ;
            if(optional.isPresent()){

                formateur = optional.get();

            }

            else {

                throw new RuntimeException("Trainer not found for email  ::  "+Email )  ;

            }

            return formateur ;

        }
        public Formateur getFormateurByNom(String nom  ){

            Optional<Formateur> optional=formateurDAO.findFormateurBynom(nom) ;

            Formateur formateur ;

            if(optional.isPresent()){

                formateur = optional.get();


            }

            else {

                throw new RuntimeException("formateur not found for name  ::  "+nom  )  ;


            }

            return formateur ;


        }
    public Formateur getFormateurByUsername(String username  ){

        Optional<Formateur> optional=formateurDAO.findFormateurByUsername(username) ;

        Formateur formateur ;

        if(optional.isPresent()){

            formateur = optional.get();


        }

        else {

            throw new RuntimeException("formateur not found for name  ::  "+username  )  ;


        }

        return formateur ;


    }
    public Formateur getFormateurById(long id  ){

        Optional<Formateur> optional=formateurDAO.findById(id) ;

        Formateur formateur ;

        if(optional.isPresent()){

            formateur = optional.get();


        }

        else {

            throw new RuntimeException("formateur not found for id  ::  "+id  )  ;


        }

        return formateur ;


    }

        public Formateur createNewFormateur(Formateur formateur ){

            formateur.setPassword(passwordEncoder.encode(formateur.getPassword()));


            return formateurDAO.save(formateur);


        }
        public void initFormateur() throws ParseException {

            String dateString = "05/12/2002";
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = dateFormat.parse(dateString);

            var formateur = new RegisterRequest().builder()
                    .nom("formateur")
                    .email("trao@os.com")
                    .password(passwordEncoder.encode("nam"))
                    .phoneNumber("12321085")
                    .username("jugking")
                    .role(Role.FORMATEUR)
                    .dateNaissance(date)
                    .build();

            System.out.println("Formateur token: "+ authenticationService.register(formateur).getToken() );






        }


        public void rem_formateur(long id ){

            formateurDAO.findById(id)
                    .orElseThrow(() -> new RuntimeException("formateur not found with id: " + id));

            formateurDAO.deleteById(id);



        }



        public Formateur update_formateur(Formateur formateur){

            if (formateur==null||formateur.getId() == null) {
                // Handle the case where ID is not set or invalid
                // You might want to throw an exception or handle it appropriately
                throw new IllegalArgumentException("Invalid formateur ID for update");
            }
            return formateurDAO.save(formateur) ;



        }

    }



