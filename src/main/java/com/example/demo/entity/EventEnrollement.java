package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "event_enrollement", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"event_id", "student_id"})
})

public class EventEnrollement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eventRating")
    private int Rating;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    // Additional attributes, constructors, getters, and setters as needed
}
