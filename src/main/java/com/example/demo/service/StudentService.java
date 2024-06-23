package com.example.demo.service;


import com.example.demo.auth.AuthenticationService;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.dao.StudentDAO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Role;
import com.example.demo.entity.Student;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private final PasswordEncoder passwordEncoder ;

    @Autowired
   private StudentDAO studentDAO ;

    @Autowired
    private AuthenticationService authenticationService;




    public Iterable<Student> getAllStudents(){


        return  this.studentDAO.findAll();


    }

    public Iterable<Student> getStudentOfFormation(Long formation_id){

        Iterable<Student> students=this.studentDAO.getStudentsByFormation(formation_id);
        if(students!=null) System.out.println("there are no students !!  ");

        return students;


    }

    public Student getStudentByEmail(String Email  ){

        Optional<Student> optional=studentDAO.findStudentByEmail(Email) ;

        Student stdnt ;
        if(optional.isPresent()){

            stdnt = optional.get();

        }

        else {

            throw new RuntimeException("Student not found for email  ::  "+Email )  ;

        }

        return stdnt ;


    }
    public Student getStudentByUsername(String username  ){

        log.info("Received request to fetch student with username: {}", username);

        Optional<Student> optional=studentDAO.findStudentByUsername(username) ;

        Student stdnt ;
        if(optional.isPresent()){

            stdnt = optional.get();

        }

        else {

            throw new RuntimeException("Student not found for username  ::  "+username )  ;

        }

        return stdnt ;


    }
    public Student getstudentbyId(Long id ){
        Optional<Student> optional=studentDAO.findById(id) ;

        Student stdnt;
        if(optional.isPresent()) stdnt=optional.get();
        else {

            throw new RuntimeException("Student not found for id  ::  "+id  )  ;


        }

        return stdnt ;



    }
    public Student getStudentbyNom(String nom  ){

        Optional<Student> optional=studentDAO.findStudentByNom(nom) ;

        Student stdnt ;

        if(optional.isPresent()){

            stdnt = optional.get();


        }

        else {

            throw new RuntimeException("Student not found for name  ::  "+nom  )  ;


        }

        return stdnt ;


    }

    public Student createNewStudent(Student student ){

        student.setPassword(passwordEncoder.encode(student.getPassword()));

        studentDAO.save(student);
        log.info("Student {} is saved", student.getId());

        return student;


    }
    public void initStudent() throws ParseException {

        String dateString = "05/12/2002";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date date = dateFormat.parse(dateString);


        var student = new RegisterRequest().builder()
                 .nom("ssss")
                .email("smlfjkqsmldjf@sqf.com")
                .password(passwordEncoder.encode("smdlfjkqs"))
                 .phoneNumber("2112058")
                 .username("ss")
                 .role(Role.STUDENT)
                 .dateNaissance(date)

                .build();

        System.out.println("Student token: "+ authenticationService.register(student).getToken() );

    }

    public void updateStudentImage(Long studentId, String imageUrl,String publicID) {
        Optional<Student> optionalStudent = studentDAO.findById(studentId);
        if (optionalStudent.isPresent()) {

            Student student = optionalStudent.get();
            student.setImageUrl(imageUrl);
            student.setPublicId(publicID);
            System.out.println("public id in updation kekkkkwww  : "+student.getPublicId());
            // Save the updated student object back to the database
            studentDAO.save(student);
            System.out.println("image updated");
        } else {
            throw new IllegalArgumentException("Student not found with ID: " + studentId);
        }

    }

  public String  getPublicIdFromStudentData(Long id ){

        String public_id= this.getstudentbyId(id) .getPublicId() ;

        if (public_id!=null) return public_id ;

      System.out.println("public_id is null ");

return null ;



  }

    public void rem_student(Long id ){

        studentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("student not found with id: " + id));

        studentDAO.deleteById(id);


    }



    public Student update_student(Student student){

        if (student==null||student.getId() == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid student ID for update");
        }
        return studentDAO.save(student) ;



    }

}
