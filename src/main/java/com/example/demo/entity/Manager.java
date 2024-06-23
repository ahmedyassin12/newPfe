package com.example.demo.entity;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@SuperBuilder
@DiscriminatorValue("MANAGER")
public class Manager extends Person {


    // Office location
    @Column(name = "officeLocation")
    private String officeLocation;





}
