package com.example.demo.service;

import com.example.demo.dao.FormationDAO;
import com.example.demo.entity.Formation;
import com.example.demo.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormationService {
    @Autowired
    private FormationDAO formationDAO ;
    public Iterable<Formation> getAllFormation(){


        return  this.formationDAO.findAll();


    }

    public Iterable<Formation> getFormationsForFormateur(Long formateur_id ) {


        return this.formationDAO.getFormationsForFormateur(formateur_id);


    }

    public void updateFormationImage(Long formationId, String imageUrl,String publicID) {
        Optional<Formation> optionalFormation = formationDAO.findById(formationId);
        if (optionalFormation.isPresent()) {

            Formation formation = optionalFormation.get();
            formation.setImageUrl(imageUrl);
            formation.setPublicId(publicID);
            System.out.println("public id in updation kekkkkwww  : "+formation.getPublicId());
            // Save the updated student object back to the database
            formationDAO.save(formation);
            System.out.println("image updated");
        } else {
            throw new IllegalArgumentException("formation not found with ID: " + formationId);
        }

    }
    public String  getPublicIdFromFormationData(Long id ){

        String public_id= this.getFormationById(id) .getPublicId() ;

        if (public_id!=null) return public_id ;

        System.out.println("public_id is null hehe");

        return null ;



    }
    public Iterable<Formation> getFormationsforEnrolledStudent(Long student_id ) {


        return this.formationDAO.getFormationsforEnrolledStudent( student_id );

    }

    public Formation getFormationById(Long id ){
        Optional<Formation> optional=formationDAO.findById(id) ;

        Formation formation;
        if(optional.isPresent()) formation=optional.get();
        else {

            throw new RuntimeException("formation not found for id  ::  "+id  )  ;


        }

        return formation ;



    }
    public Formation getFormationbyNom(String nom  ){

        Optional<Formation> optional=formationDAO.findFormationByNom(nom) ;

        Formation formation ;

        if(optional.isPresent()){

            formation = optional.get();


        }

        else {

            throw new RuntimeException("Formation not found for name  ::  "+nom  )  ;


        }

        return formation ;


    }

    public Formation createNewFormation(Formation formation ){


        formationDAO.save(formation);
        log.info("Formation {} is saved", formation.getId());

        return formation;


    }

    public void rem_Formation(Long id ){

        formationDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("formation not found with id: " + id));

        formationDAO.deleteById(id);


    }
    public Formation update_formation(Formation formation){

        if (formation==null||formation.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid formation ID for update");
        }
        return formationDAO.save(formation) ;



    }

}
