package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.EmailUpdatedResponseDto;
import com.LibraryManagment.System.DTO.StudentRequestDto;
import com.LibraryManagment.System.DTO.StudentUpdateEmailRequestDto;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Entity.Student;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Exception.StudentNotFoundException;
import com.LibraryManagment.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
        public void addStudent(StudentRequestDto studentRequestDto) throws RuntimeException{
            // create Student
            Student student = new Student();
            student.setName(studentRequestDto.getName());
            student.setAge(studentRequestDto.getAge());
            student.setEmail(studentRequestDto.getEmail());
            student.setDepartment(studentRequestDto.getDepartment());

            LibraryCard card = new LibraryCard();
            student.setCard(card);
            card.setCardStatus(cardStatus.ACTIVATE);
            card.setStudent(student);

            studentRepository.save(student);
        }

    public Student findStudentByEmail(String email) throws StudentNotFoundException {
            Student student = studentRepository.findByEmail(email);
        System.out.println(student);
            if(student == null)throw new StudentNotFoundException("Student Not found");
            return student;

    }

    public EmailUpdatedResponseDto updateMobile(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto) throws StudentNotFoundException {
            Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
            if(student == null) throw new StudentNotFoundException("Enter valid Student Id");
            student.setEmail(studentUpdateEmailRequestDto.getEmail());
            // update
        Student updatedStudent = studentRepository.save(student);

        EmailUpdatedResponseDto emailUpdatedResponseDTo= new EmailUpdatedResponseDto();
        emailUpdatedResponseDTo.setId(updatedStudent.getId());
        emailUpdatedResponseDTo.setName(updatedStudent.getName());
        emailUpdatedResponseDTo.setEmail(updatedStudent.getEmail());

        return emailUpdatedResponseDTo;
    }

    public void deactivateCards(int studentId) throws Exception {
            Student student = studentRepository.findById(studentId).get();
            if(student == null)throw new StudentNotFoundException("Enter a valid student Id!");

            student.getCard().setCardStatus(cardStatus.DEACTIVATED);
            studentRepository.save(student);
    }

    public void activateCards(int studentId) throws Exception {
            Student student = studentRepository.findById(studentId).get();
            if(student == null)throw new StudentNotFoundException("Enter a valid student Id!");

            student.getCard().setCardStatus(cardStatus.ACTIVATE);
            studentRepository.save(student);
    }
}
