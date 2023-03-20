package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.BookRequestDto;
import com.LibraryManagment.System.DTO.BookResponseDto;
import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Enum.Genre;
import com.LibraryManagment.System.Repository.AuthorRepository;
import com.LibraryManagment.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    public BookResponseDto addBook(BookRequestDto bookRequestDto) throws Exception {

        Author author = authorRepository.findById(bookRequestDto.getAuthorId()).get();

        Book book = new Book();
        book.setGenre(bookRequestDto.getGenre());
        book.setTitle(bookRequestDto.getTitle());
        book.setPrice(bookRequestDto.getPrice());
        book.setIssued(false);
        book.setAuthor(author);
        author.getBooks().add(book);
        authorRepository.save(author);

        BookResponseDto bookResponseDto = new BookResponseDto();
        bookResponseDto.setTitle(book.getTitle());
        bookResponseDto.setPrice(book.getPrice());

        return bookResponseDto;

    }

    public List<Book> getByGenre(String genre) {
        List<Book> bookList = bookRepository.findByGenre(genre);
        return bookList;
    }

    public void deleteBookById(int bookId) throws Exception {
        Book book = bookRepository.findById(bookId).get();
        if(book == null)throw new Exception("Enter a valid book Id!");

        bookRepository.delete(book);
    }
}
