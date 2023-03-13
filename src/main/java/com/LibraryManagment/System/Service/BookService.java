package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Repository.AuthorRepository;
import com.LibraryManagment.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    public void addBook(Book book) throws Exception {
        Author author;
        try{
            author = authorRepository.findById(book.getAuthor().getId()).get();
        }catch (Exception e){
            throw  new Exception("Author not present");
        }

        List<Book> bookWritten = author.getBooks();
        bookWritten.add(book);
        authorRepository.save(author);

    }
}
