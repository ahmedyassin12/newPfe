package com.example.demo.service;

import com.example.demo.dao.EventEnrollementDAO;
import com.example.demo.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EventEnrollementService {


        @Autowired
        private EventEnrollementDAO enrollementDAO;


        public Iterable<EventEnrollement> getAllEnrollements() {
            return enrollementDAO.findAll();
        }

    public Iterable<EventEnrollement> getEventEnrollementForStudent(Long student_id ) {


        return enrollementDAO.getEventEnrollementForStudent(student_id);


    }

    public     Iterable<EventEnrollement> getEventEnrollement(Long event_id){
        Iterable<EventEnrollement> enrollements=enrollementDAO.getEventEnrollement(event_id);

        if (enrollements==null) System.out.println("there is no enrollement for event " +event_id);

        return enrollements;


    }

    public EventEnrollement getEnrollementById(Long id) {

            Optional<EventEnrollement> optional = enrollementDAO.findById(id);

            EventEnrollement enrollement;
            if (optional.isPresent()) {
                enrollement = optional.get();
            } else {
                throw new RuntimeException("Enrollement not found for id :: " + id);
            }
            return enrollement;
        }

        public EventEnrollement createNewEnrollement(EventEnrollement enrollement) {





            enrollementDAO.save(enrollement);
            log.info("Enrollement {} is saved", enrollement.getId());
            return enrollement;


        }

        public void removeEnrollement(Long id) {
            enrollementDAO.findById(id)
                    .orElseThrow(() -> new RuntimeException("Enrollement not found with id: " + id));
            enrollementDAO.deleteById(id);
        }

        public EventEnrollement updateEnrollement(EventEnrollement enrollement) {
            if (enrollement == null || enrollement.getId() == null) {
                throw new IllegalArgumentException("Invalid enrollement ID for update");
            }
            return enrollementDAO.save(enrollement);
        }


    }

