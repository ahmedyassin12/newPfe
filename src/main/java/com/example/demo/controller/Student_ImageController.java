package com.example.demo.controller;
import com.example.demo.service.CloudinaryService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v6/images")
public class Student_ImageController {



    @Autowired
    CloudinaryService cloudinaryService;



    @Autowired
    private StudentService studentService ;



    @PostMapping("/upload_image/{student_id}")
    public ResponseEntity<String> upload_image(@RequestParam("file") MultipartFile multipartFile ,@PathVariable("student_id") Long student_id) throws IOException {
        if(studentService.getstudentbyId(student_id)==null)         return new ResponseEntity<>("there is no student with id   "+student_id, HttpStatus.BAD_REQUEST);



        try {
            BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
            if (bi == null) {
                return new ResponseEntity<>("Image non valide!", HttpStatus.BAD_REQUEST);
            }
        Map result = cloudinaryService.upload(multipartFile);



        studentService.updateStudentImage(student_id,(String) result.get("url"),(String) result.get("public_id") );
            System.out.println("public_id = "+result.get("public_id"));

            return new ResponseEntity<>("image ajoutée avec succès !", HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Erreur de lecture du fichier image", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Erreur inattendue", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("/delete_image/{id}")
    public ResponseEntity<String> delete_image(@PathVariable("id") Long id) {



        String public_id = studentService.getPublicIdFromStudentData(id) ;
        try {
            cloudinaryService.delete(public_id);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to delete image from Cloudinary", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        studentService.updateStudentImage(id,null,null);

        return new ResponseEntity<>("image supprimée !", HttpStatus.OK);



    }





}