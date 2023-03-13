package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {
}
