package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query(value = "select * from author",nativeQuery = true)
    public List<Author> findAllAuthors();
}
