package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.BookByGenreResponseDto;
import com.LibraryManagment.System.DTO.BookRequestDto;
import com.LibraryManagment.System.DTO.BookResponseDto;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Enum.Genre;
import com.LibraryManagment.System.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookResponseDto addBook(@RequestBody BookRequestDto bookRequestDto) throws Exception {
        return  bookService.addBook(bookRequestDto);
    }

//    error 500 Internal Server Error check req parm
    @GetMapping("/byGenre")
    public List<BookByGenreResponseDto> getByGenre(@RequestParam String genre){
        return bookService.getByGenre(genre);
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public String deleteBookById(@PathVariable("bookId") int bookId) throws Exception {
        bookService.deleteBookById(bookId);
        return "Book deleted from data bases";
    }

    // deactivate all cards having book return date crossed current date and time
    @PutMapping("/periodicDeactivation")
    public String periodicDeactivation(){
        bookService.periodicDeactivation();
        return "Periodic Deactivation Process Completed";
    }
}
