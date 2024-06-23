package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "paiement_id")
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paiement_id")
    private long paiement_id ;

    @Column(name = "amount")
    private int amount ;

    @Column(name = "paimentDate",nullable = false)
    private LocalDate paimentDate ;

    @ManyToOne
    @JoinColumn(name="enrollement_id",nullable = false)
    private Enrollement enrollement ;


  /*  @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

*/


}
