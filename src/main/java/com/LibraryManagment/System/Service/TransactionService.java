package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.*;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Entity.Transaction;
import com.LibraryManagment.System.Enum.TransactionStatus;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Exception.BookAlreadyIssuedException;
import com.LibraryManagment.System.Exception.BookNotFoundException;
import com.LibraryManagment.System.Exception.CardNotActivatedException;
import com.LibraryManagment.System.Exception.CardNotFoundException;
import com.LibraryManagment.System.Repository.BookRepository;
import com.LibraryManagment.System.Repository.LibraryCardRepository;
import com.LibraryManagment.System.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryCardRepository cardRepository;

    @Autowired
    JavaMailSender emailSender;
//    why autuwired javamail sender didn't have any function of class , is it present in dependency

    // update card no in book class as well and book issued to
    public IssueResponseDto addIssueTransaction(IssueRequestDto issueRequestDto) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(true);

        LibraryCard card;
        try{
            card= cardRepository.findById(issueRequestDto.getCardId()).get();
        }catch (NoSuchElementException e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid card id");
        }

        Book book;
        try{
            book =bookRepository.findById(issueRequestDto.getBookId()).get();
        } catch (NoSuchElementException e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setCard(card);
            transaction.setMessage("Invalid book Id");
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Invalid book Id");
        }


        if(card.getCardStatus() != cardStatus.ACTIVATE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setCard(card);
            transaction.setMessage("card not Activated");
            transactionRepository.save(transaction);
            throw new CardNotActivatedException("Your card is not Activated");
        }
        if(book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setCard(card);
            transaction.setMessage("book is already issued");
            transactionRepository.save(transaction);

            throw new BookAlreadyIssuedException("Sorry! book is already issued");
        }

        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setCard(card);
        transaction.setMessage("transaction successful");
        transaction.setBook(book);
        transactionRepository.save(transaction);

        book.setIssued(true);
        book.setCard(card);
        book.getListOfTransaction().add(transaction);
        card.setUpdationDate(Date.from(Instant.now()));
        cardRepository.save(card);

        IssueResponseDto issueResponseDto = new IssueResponseDto();
        issueResponseDto.setTransactionStatus(transaction.getTransactionStatus().toString());
        issueResponseDto.setTransactionId(transaction.getTransactionNumber());
        issueResponseDto.setOperation("Book issue");
        issueResponseDto.setAuthorName(book.getAuthor().getName());
        issueResponseDto.setBookTitle(book.getTitle());

        String message = String.format("Dear Student,\n New Book has been issued on you card. \nTransaction No: '%s' \nCard No: '%s'\nBook Name: %s",transaction.getTransactionNumber(),card.getCardNo(),book.getTitle());

        sendSimpleMessage(card.getStudent().getEmail(),"Library Management System | book-issued",message);

        return issueResponseDto;

    }

    // update card no in book class as well
    public ReturnRespondDto returnBookTransaction(ReturnRequestDto returnRequestDto) throws Exception {

        Transaction transaction = new Transaction();
        transaction.setTransactionNumber(String.valueOf(UUID.randomUUID()));
        transaction.setIssueOperation(false);

        LibraryCard card;
        try{
            card = cardRepository.findById(returnRequestDto.getCardId()).get();
        }catch (NoSuchElementException e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid card id");
            transactionRepository.save(transaction);
            throw new CardNotFoundException("Invalid card id");
        }

        Book book;
        try{
            book = bookRepository.findById(returnRequestDto.getBookId()).get();
        }catch (NoSuchElementException e){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("Invalid book Id");
            transactionRepository.save(transaction);
            throw new BookNotFoundException("Invalid book Id");
        }

        if(card.getCardStatus() != cardStatus.ACTIVATE){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("card not Activated");
            transactionRepository.save(transaction);
            throw new CardNotActivatedException("Your card is not Activated");
        }
        if(!book.isIssued()){
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            transaction.setMessage("book is not issued, check your book id!");
            transactionRepository.save(transaction);

            throw new BookAlreadyIssuedException("Sorry! book is already issued, check your book id!");
        }
        transaction.setCard(card);
        transaction.setBook(book);
        transaction.setTransactionStatus(TransactionStatus.SUCCESS);
        transaction.setMessage("transaction successful");
        transactionRepository.save(transaction);

        book.setIssued(false);
        book.setCard(null);
        book.getListOfTransaction().add(transaction);
        card.setUpdationDate(Date.from(Instant.now()));
        cardRepository.save(card);

        ReturnRespondDto returnRespondDto = new ReturnRespondDto();
        returnRespondDto.setTransactionId(transaction.getTransactionNumber());
        returnRespondDto.setBookTitle(book.getTitle());
        returnRespondDto.setTransactionStatus(transaction.getTransactionStatus().toString());
        returnRespondDto.setOperation("Book return type");
        returnRespondDto.setAuthorName(book.getAuthor().getName());

        String message = String.format("Dear Student,\n Book has been return to the library form your card. \nTransaction No: '%s' \nCard No: '%s'\nBook Name: %s",transaction.getTransactionNumber(),card.getCardNo(),book.getTitle());

        sendSimpleMessage(card.getStudent().getEmail(),"Library Management System | Book return",message);

        return returnRespondDto;

    }

    public List<getAllTnxForCardResponseDto> getAllTransaction(int cardId) {
        List<Transaction> transactionList = transactionRepository.getAllTransaction(cardId);

        List<getAllTnxForCardResponseDto> resultSet = new ArrayList<>();
        for(Transaction transaction : transactionList){

            getAllTnxForCardResponseDto getAllTnxForCardResponseDto = new getAllTnxForCardResponseDto();

            getAllTnxForCardResponseDto.setTransactionNumber(transaction.getTransactionNumber());
            getAllTnxForCardResponseDto.setTransactionDate(transaction.getTransactionDate());
            getAllTnxForCardResponseDto.setTransactionStatus(transaction.getTransactionStatus());
            getAllTnxForCardResponseDto.setMessage(transaction.getMessage());

            if(transaction.isIssueOperation()){
                getAllTnxForCardResponseDto.setOperation("Book Issue");
            }else{
                getAllTnxForCardResponseDto.setOperation("Book return");
            }

            resultSet.add(getAllTnxForCardResponseDto);
        }
        return resultSet;
    }

    public List<getAllTnxForCardResponseDto> getAllSuccessfulTransaction(int cardId) {
        List<Transaction> transactionList = transactionRepository.getAllTransaction(cardId);

        List<getAllTnxForCardResponseDto> resultSet = new ArrayList<>();
        for(Transaction transaction : transactionList){

            if(transaction.getTransactionStatus().equals(TransactionStatus.SUCCESS)){

                getAllTnxForCardResponseDto getAllTnxForCardResponseDto = new getAllTnxForCardResponseDto();

                getAllTnxForCardResponseDto.setTransactionNumber(transaction.getTransactionNumber());
                getAllTnxForCardResponseDto.setTransactionDate(transaction.getTransactionDate());
                getAllTnxForCardResponseDto.setTransactionStatus(transaction.getTransactionStatus());
                getAllTnxForCardResponseDto.setMessage(transaction.getMessage());

                if(transaction.isIssueOperation()){
                    getAllTnxForCardResponseDto.setOperation("Book Issue");
                }else{
                    getAllTnxForCardResponseDto.setOperation("Book return");
                }

                resultSet.add(getAllTnxForCardResponseDto);
            }
        }
        return resultSet;
    }
    public void sendSimpleMessage(
            String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testingdevnew@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);

    }
}
