package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.EmailUpdatedResponseDto;
import com.LibraryManagment.System.DTO.ExceptionMessageResponseDto;
import com.LibraryManagment.System.DTO.StudentRequestDto;
import com.LibraryManagment.System.DTO.StudentUpdateEmailRequestDto;
import com.LibraryManagment.System.Entity.Student;
import com.LibraryManagment.System.Exception.StudentNotFoundException;
import com.LibraryManagment.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequestDto studentRequestDto){

        try{
            studentService.addStudent(studentRequestDto);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Error occurred try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Student added", HttpStatus.CREATED);
    }

//   not receive any student info check and add query
    @GetMapping("/findByEmail")
    public ResponseEntity findStudentByEmail(@RequestParam("email") String email){
        Student student;
        try{
            student =studentService.findStudentByEmail(email);

        }catch (StudentNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return new ResponseEntity<>(exception,HttpStatus.valueOf(204)); // 204 no content
        }
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateEmail")
    public ResponseEntity updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        EmailUpdatedResponseDto emailUpdatedResponseDto;

        try{
            emailUpdatedResponseDto = studentService.updateMobile(studentUpdateEmailRequestDto);
        }catch (StudentNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emailUpdatedResponseDto,HttpStatus.CREATED);
    }

    @PutMapping("/deactivateCards/{studentId}")
    public ResponseEntity deactivateCards(@PathVariable int studentId) throws Exception {

        try{
            studentService.deactivateCards(studentId);
        }catch (StudentNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Card has been deactivated",HttpStatus.CREATED);
    }

    @PutMapping("/activateCards/{studentId}")
    public ResponseEntity activateCards(@PathVariable int studentId) throws Exception {
        try{
            studentService.activateCards(studentId);
        }catch (StudentNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return  new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Card has been Activated",HttpStatus.CREATED);
    }
}
