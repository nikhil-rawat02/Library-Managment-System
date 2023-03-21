package com.LibraryManagment.System.Repository;

import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Enum.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {


    @Query(value = "select * from book b where b.genre=:genre",nativeQuery = true)
    public List<Book> findByGenre(String genre);

    @Query(value = "Select id from book where returnDate < now()",nativeQuery = true)
    List<Integer> getBooks();
}
