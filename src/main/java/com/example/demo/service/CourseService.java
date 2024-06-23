package com.example.demo.service;

import com.example.demo.dao.CourseDAO;
import com.example.demo.entity.Course;
import com.example.demo.entity.Enrollement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CourseService {

    @Autowired
    private CourseDAO courseDAO;

    @Autowired
    EnrollementService enrollementService ;

    public Iterable<Course> getAllCourses() {


        return this.courseDAO.findAll();


    }

    public Iterable<Course> getFormateurCourses(Long formateur_id,Long formation_id){


        return this.courseDAO.getFormateurCourses(formateur_id,formation_id) ;


    }

    public Iterable<Course> findCoursesByStudentAndFormation(Long student_id,Long formation_id){

        Iterable<Course> courses=this.courseDAO.findCoursesByStudentAndFormation(student_id,formation_id);
        if(courses!=null) System.out.println("No paid courses ");

        return courses;


    }

    public Iterable<Course> getFormationCourses(@Param("formation_id") Long formation_id){

        Iterable<Course> courses=this.courseDAO.getFormationCourses(formation_id);
        if(courses!=null) System.out.println("No available courses ");

        return courses;


    }

    public Course getCourseById(Long id) {
        Optional<Course> optional = courseDAO.findById(id);

        Course course;
        if (optional.isPresent()) course = optional.get();
        else {

            throw new RuntimeException("Course not found for id  ::  " + id);


        }

        return course;


    }

    public Course getCourseByNom(String nom) {

        Optional<Course> optional = courseDAO.findCourseByNom(nom);

        Course course;

        if (optional.isPresent()) {

            course = optional.get();


        } else {

            throw new RuntimeException("course not found for name  ::  " + nom);


        }

        return course;


    }

    public Course createNewCourse(Course course) {


        courseDAO.save(course);
        log.info("course {} is saved", course.getId());

        return course;


    }

    public void rem_Course(Long id) {

        courseDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("course not found with id: " + id));

        courseDAO.deleteById(id);


    }

    public Course update_Course(Course course) {

        if (course == null) {
            // Handle the case where ID is not set or invalid
            // You might want to throw an exception or handle it appropriately
            throw new IllegalArgumentException("Invalid course ID for update");
        }
        return courseDAO.save(course);


    }


    public void updatepdf_url(Long lectureId,String pdfUrl,String public_Id){

        Optional<Course> OptionalLecture = courseDAO.findById(lectureId) ;

        if (OptionalLecture.isPresent()){

            Course lecture=OptionalLecture.get() ;
            lecture.setPdf_url(pdfUrl);
            lecture.setPublicId(public_Id);

            courseDAO.save(lecture) ;

        }
     else {
        throw new IllegalArgumentException("lecture not found with ID: " + lectureId);
    }

    }

    public String  getPublicIdFromCourseData(Long id ){

        String public_id= this.getCourseById(id) .getPublicId() ;

        if (public_id!=null) return public_id ;

        return null ;



    }
    public void updatevideo_url(Long course_id,String videoUrl,String public_id){

        Optional<Course> OptionalCourse = courseDAO.findById(course_id) ;

        if (OptionalCourse.isPresent()){

            Course course=OptionalCourse.get() ;
            course.setVideo_url(videoUrl);
            course.setPublicId(public_id);
            courseDAO.save(course) ;

        }
        else {
            throw new IllegalArgumentException("course not found with ID: " + course_id);
        }

    }


}
