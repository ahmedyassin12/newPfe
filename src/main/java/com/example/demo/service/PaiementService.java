package com.example.demo.service;

import com.example.demo.dao.PaiementDAO;
import com.example.demo.entity.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaiementService {


    @Autowired
    private PaiementDAO paiementRepository;

    @Autowired
    private EnrollementService enrollementService ;
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }

    public Iterable<Paiement> getPaiementsOfEnrollement( Long enrollementId){


        return paiementRepository.getPaiementsOfEnrollement(enrollementId);


    }


    public Paiement getpaiementById(long id ){
        Optional<Paiement> optional=paiementRepository.findById(id) ;

        Paiement paiement;
        if(optional.isPresent()) paiement=optional.get();
        else {

            throw new RuntimeException("paiement not found for id  ::  "+id  )  ;


        }

        return paiement ;



    }
    public List<Paiement> getPaiementByStudent_id(long student_Id) {

        List<Paiement> paiements = paiementRepository.findByStudentId(student_Id);


        if (paiements.isEmpty()) {
            throw new RuntimeException("Paiments not found for student_id :: " + student_Id);
        }
        return paiements;


    }


    public Paiement createPaiement(Paiement paiement) {

/*
        paiement.setEnrollement(enrollementService.getEnrollementById(paiement.getEnrollement().getEnrollement_id()));

        if(paiement.getEnrollement().getFormation() !=null)
            paiement.setFormation(paiement.getEnrollement().getFormation());

        else paiement.setSession(paiement.getEnrollement().getSession());


        paiement.setStudent(paiement.getEnrollement().getStudent());
        */
        return paiementRepository.save(paiement);
    }

    public Paiement updatePaiement( Paiement paiment) {
        if (paiment == null ) {
            throw new IllegalArgumentException("Invalid enrollement ID for update");
        }
        paiementRepository.save(paiment) ;
        return paiment;

    }

    public void deletePaiement(long paiementId) {
        paiementRepository.deleteById(paiementId);
    }







}
