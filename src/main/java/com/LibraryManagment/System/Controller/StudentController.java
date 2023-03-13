package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.Entity.Student;
import com.LibraryManagment.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student){

        try{
            studentService.addStudent(student);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Error occurred try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Student added", HttpStatus.CREATED);
    }
}
