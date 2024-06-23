        package com.example.demo.entity;
        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;
        import lombok.ToString;

        @Entity
        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @ToString
        public class Course {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "course_id")
            private long id ;
            @Column(name = "course_name")
            private String nom;


            @Column(name = "course_description")
            private String course_description;

            @Column(name = "VideoUrl")
            private String video_url;

            @Column(name = "PdfUrl")
            private String pdf_url;

            @Column(name="public_id")
            private String publicId ;



            @OneToOne(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
            private Ressources ressources ;

            @ManyToOne
            @JoinColumn(name ="Formation_id" ,nullable = false)
            private Formation formation ;





        }
