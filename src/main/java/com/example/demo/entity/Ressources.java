package com.example.demo.entity;

import jakarta.persistence.*;

@Entity

public class Ressources {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ressource_id")
    private long ressource_id ;

    @Column(name = "ressource_type")
    private String ressource_type;

    @Column(name = "ressource_url")
    private String ressource_url;


    @OneToOne
    @JoinColumn(name="course_id")
    private Course course ;



}


