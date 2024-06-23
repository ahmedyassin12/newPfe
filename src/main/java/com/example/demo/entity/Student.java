package com.example.demo.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@SuperBuilder
@DiscriminatorValue("STUDENT")
public class Student extends Person {


    @Column(name="imageUrl")
    private String imageUrl ;

    @Column(name="Public_id")
    private String publicId ;


    @Column(name="age")
   @Min(10)
    private int age ;






//additional attributes :



}

