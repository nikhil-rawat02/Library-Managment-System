package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.ListOfAllBookResponseDto;
import com.LibraryManagment.System.DTO.lastUpdateDateAndStatusRespondDto;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Exception.CardNotActivatedException;
import com.LibraryManagment.System.Exception.CardNotFoundException;
import com.LibraryManagment.System.Service.LibraryCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getAllBook(@PathVariable int cardNo) {
        List<ListOfAllBookResponseDto> books;
        try{
            books = libraryCardService.getAllBook(cardNo);
        }catch (CardNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books,HttpStatus.ACCEPTED);
    }

    @GetMapping("/lastUpdateDate/{cardId}")
    public lastUpdateDateAndStatusRespondDto lastUpdateDateAndStatusRespondDto(@PathVariable("cardId") int cardId) throws Exception {
        return libraryCardService.lastUpdateDateAndStatus(cardId);
    }
}

