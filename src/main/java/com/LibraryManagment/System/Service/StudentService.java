package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Entity.Student;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentService {

    @Autowired
    StudentRepository studentRepository;
        public void addStudent(Student student) throws RuntimeException{
            LibraryCard card = new LibraryCard();

            card.setCardStatus(cardStatus.ACTIVATED);
            card.setValidTill("01/2023");
            card.setStudent(student);

            student.setCard(card);studentRepository.save(student);
        }

}
