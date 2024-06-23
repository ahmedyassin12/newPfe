    package com.example.demo.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;

    import java.time.LocalDate;
    import java.util.List;
    import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    @Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Table(uniqueConstraints = {
            @UniqueConstraint(columnNames = {"student_id", "formation_id"}),
    })

    public class Enrollement {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "enrollement_id")
        private Long enrollement_id ;

        @Column(name="paiement_Status")
        private String paiement_Status ;

        @Column(name="rating")
        private int rating ;



        @Column(name = "Enrollement_date")
        private LocalDate enrollement_date;

        @OneToMany(mappedBy = "enrollement",fetch = FetchType.EAGER,cascade =CascadeType.ALL)
        private List <Paiement> paiement ;

        @JsonIgnoreProperties({"student"}) // Prevent infinite recursion during serialization
        @ManyToOne
        @JoinColumn(name = "student_id", nullable = false)
        private Student student;

        @ManyToOne
        @JoinColumn(name = "Formation_id")
        private Formation formation;





    }
