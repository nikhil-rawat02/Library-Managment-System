package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.BookByGenreResponseDto;
import com.LibraryManagment.System.DTO.BookRequestDto;
import com.LibraryManagment.System.DTO.BookResponseDto;
import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Enum.Genre;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Repository.AuthorRepository;
import com.LibraryManagment.System.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TransactionService transactionService;

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

    public List<BookByGenreResponseDto> getByGenre(String genre) {

        List<Book> bookList = bookRepository.findByGenre(genre);
        List<BookByGenreResponseDto>bookResponseDtos = new ArrayList<>();

        for(Book book: bookList){
            BookByGenreResponseDto bookResponseDto = new BookByGenreResponseDto();
            bookResponseDto.setId(book.getId());
            bookResponseDto.setTitle(book.getTitle());
            bookResponseDto.setPrice(book.getPrice());
            bookResponseDto.setAuthorName(book.getAuthor().getName());
            bookResponseDtos.add(bookResponseDto);
        }
        return bookResponseDtos;
    }

    public void deleteBookById(int bookId) throws Exception {
        Book book = bookRepository.findById(bookId).get();
        if(book == null)throw new Exception("Enter a valid book Id!");

        bookRepository.delete(book);
    }

    public void periodicDeactivation() {
        List<Integer> bookIdList = bookRepository.getBooks();
        for(Integer bookId : bookIdList){
            Book book = bookRepository.findById(bookId).get();
            book.getCard().setCardStatus(cardStatus.DEACTIVATED);
            String email = book.getCard().getStudent().getEmail();
            String message = "Dear student you card has been Blocked because " + book.getTitle() + " return date has been crossed kindly return or re-issue book from library";
            transactionService.sendSimpleMessage(email,"Library Management System | Card Blocked",message);

        }

    }
}
