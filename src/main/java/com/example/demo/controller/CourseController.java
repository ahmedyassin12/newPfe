package com.example.demo.controller;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollement;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v3/course")

@RestController
public class CourseController {
    @Autowired
    CourseService courseService;


    @GetMapping("/getAllCourses")
    public ResponseEntity<Iterable<Course>> getAllCourses() {

        Iterable<Course> courses = courseService.getAllCourses();

        return new ResponseEntity<>(courses, HttpStatus.OK);

    }

    @GetMapping("/getStudentCourses/{student_id}/{formation_id}")
    public ResponseEntity<Iterable<Course>> getStudentCourses(@PathVariable Long student_id,@PathVariable Long formation_id){

        Iterable<Course>courses=courseService.findCoursesByStudentAndFormation(student_id,formation_id) ;

        if (courses==null ) System.out.println("u need  to pay for formation");

        return  new ResponseEntity<>(courses,HttpStatus.OK) ;

    }

    @GetMapping("/getFormateurCourses/{formateur_id}/{formation_id}")
    public ResponseEntity<Iterable<Course>>getFormateurCourses (@PathVariable Long formateur_id,@PathVariable Long formation_id){

        Iterable<Course>courses=courseService.getFormateurCourses(formateur_id,formation_id) ;
        return  new ResponseEntity<>(courses,HttpStatus.OK) ;



    }

    @GetMapping("/getFormationCourses/{formation_id}")
    public Iterable<Course> getFormationCourses(@PathVariable Long formation_id){

        Iterable<Course> courses=courseService.getFormationCourses(formation_id);
        if(courses!=null) System.out.println("No available courses ");

        return courses;


    }


    @GetMapping("/getCourseById/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") Long id) {

        Course course = courseService.getCourseById(id);
        System.out.println("course  : " + course);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }

    @PostMapping({"/createNewCourse"})
    public ResponseEntity<Course> createNewCourse(@RequestBody Course course) {


        return new ResponseEntity<>(courseService.createNewCourse(course), HttpStatus.OK);



    }

    @GetMapping("/getCourseByNom/{nom}")
    public ResponseEntity<Course> getCourseByNom(@PathVariable("nom") String nom) {

        Course course = courseService.getCourseByNom(nom);
        System.out.println("course  : " + course);
        return new ResponseEntity<>(course, HttpStatus.OK);

    }


    @DeleteMapping("/rem_course/{id}")

    public ResponseEntity<?> rem_lecture(@PathVariable("id")  Long id ){

        courseService.rem_Course(id);
        return new ResponseEntity<>(HttpStatus.OK) ;


    }

    @PutMapping("/update_Course")
    public  ResponseEntity<Course> update_Course(@RequestBody Optional<Course> optionalCourse){

        Course course =optionalCourse.orElseThrow(() -> new IllegalArgumentException("Invalid course  for update"));

        return new ResponseEntity<>(courseService.update_Course(course),HttpStatus.OK);

    }


}
