package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query(value ="select * from student s where s.email=:email",nativeQuery = true)
    Student findByEmail(String email);

}
