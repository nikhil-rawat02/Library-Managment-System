package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.*;
import com.LibraryManagment.System.Exception.BookAlreadyIssuedException;
import com.LibraryManagment.System.Exception.BookNotFoundException;
import com.LibraryManagment.System.Exception.CardNotActivatedException;
import com.LibraryManagment.System.Exception.CardNotFoundException;
import com.LibraryManagment.System.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/issueBook")
    public ResponseEntity addIssueTransaction(@RequestBody IssueRequestDto issueRequestDto) throws Exception{

        IssueResponseDto issueResponseDto;
        try{
            issueResponseDto = transactionService.addIssueTransaction(issueRequestDto);
        }catch (CardNotFoundException | BookAlreadyIssuedException | CardNotActivatedException | BookNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(issueResponseDto,HttpStatus.CREATED);
    }

    @PostMapping("/returnBook")
    public ResponseEntity returnBookTransaction(@RequestBody ReturnRequestDto returnRequestDto) throws Exception {
        ReturnRespondDto returnRespondDto;
        try{
            returnRespondDto = transactionService.returnBookTransaction(returnRequestDto);
        }catch (CardNotFoundException | BookAlreadyIssuedException | CardNotActivatedException | BookNotFoundException e){
            ExceptionMessageResponseDto exception = new ExceptionMessageResponseDto();
            exception.setMessage(e.getMessage());
            return new ResponseEntity<>(exception,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(returnRespondDto, HttpStatus.CREATED);
    }

    @GetMapping("/AllTnx/{cardId}")
    public List<getAllTnxForCardResponseDto> getAllTransaction(@PathVariable int cardId){
        return transactionService.getAllTransaction(cardId);
    }

    @GetMapping("/AllSuccessfulTnx/{cardId}")
    public List<getAllTnxForCardResponseDto> getAllSuccessfulTransaction(@PathVariable int cardId){
        return transactionService.getAllSuccessfulTransaction(cardId);
    }

}
