package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.lastUpdateDateAndStatusRespondDto;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
public class LibraryCardController {

    @Autowired
    LibraryCardService libraryCardService;
//run again next time
    @GetMapping("/getAllBook/{cardNo}")
    public List<Book> getAllBook(@PathVariable int cardNo) {
        List<Book> books = libraryCardService.getAllBook(cardNo);
        return books;
    }

    @GetMapping("/lastUpdateDate/{cardId}")
    public lastUpdateDateAndStatusRespondDto lastUpdateDateAndStatusRespondDto(@PathVariable("cardId") int cardId) throws Exception {
        return libraryCardService.lastUpdateDateAndStatus(cardId);
    }
}

