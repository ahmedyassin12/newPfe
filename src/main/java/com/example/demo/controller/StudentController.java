package com.example.demo.controller;
import com.example.demo.entity.Course;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.Optional;


@RestController
public class StudentController {


@Autowired
    StudentService studentService ;


    @RequestMapping("/test")
    public String test() {
        return ("Je fonctionne correctement");
    }



  /*  @PostConstruct
    public void initStudents () throws ParseException {


        studentService.initStudent();



    }*/
  @GetMapping("/getStudentOfFormation/{formation_id}")
  public ResponseEntity<Iterable<Student>>getFormateurCourses ( @PathVariable Long formation_id){

      Iterable<Student>students=studentService.getStudentOfFormation(formation_id) ;
      return  new ResponseEntity<>(students,HttpStatus.OK) ;



  }


    @GetMapping("/getAllStudents")
    public ResponseEntity<Iterable<Student>> getAllStudents() {

        Iterable<Student> students = studentService.getAllStudents();

        return new ResponseEntity<>(students, HttpStatus.OK);

    }
    @GetMapping("/getStudentByUsername/{username}")
    public ResponseEntity<Student> getStudentByUsername(@PathVariable("username") String username ) {

        Student student = studentService.getStudentByUsername(username);

        return new ResponseEntity<>(student, HttpStatus.OK);

    }

    @GetMapping("/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {

        Student student = studentService.getstudentbyId(id);
        System.out.println("student  : " + student);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }

    @PostMapping({"/createNewStudent"})
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student) {


        return new ResponseEntity<>(studentService.createNewStudent(student), HttpStatus.OK);



    }

    @GetMapping("/getStudentByNom/{nom}")
    public ResponseEntity<Student> getStudentbyNom(@PathVariable("nom") String nom) {

        Student student = studentService.getStudentbyNom(nom);
        System.out.println("student  : " + student);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }
    @GetMapping("/getStudentByEmail/{Email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable("Email") String Email) {

        Student student = studentService.getStudentByEmail(Email);
        System.out.println("student   : " + student);
        return new ResponseEntity<>(student, HttpStatus.OK);

    }


    @DeleteMapping("/rem_student/{id}")

    public ResponseEntity<?> rem_student(@PathVariable("id")  Long id ){

        studentService.rem_student(id);
return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/update_student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student updatedStudent = studentService.update_student(student);
        return ResponseEntity.ok(updatedStudent);
    }





}
