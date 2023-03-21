package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.ListOfAllBookResponseDto;
import com.LibraryManagment.System.DTO.lastUpdateDateAndStatusRespondDto;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Exception.CardNotActivatedException;
import com.LibraryManagment.System.Exception.CardNotFoundException;
import com.LibraryManagment.System.Repository.LibraryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LibraryCardService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
    public List<ListOfAllBookResponseDto> getAllBook(int cardNo) throws CardNotFoundException {
        LibraryCard card;

        try{
            card= libraryCardRepository.findById(cardNo).get();
        }catch (Exception e){
            throw new CardNotFoundException("Enter a valid card Id");
        }
        List<Book> books = card.getBookIssued();
        List<ListOfAllBookResponseDto> responseDtos = new ArrayList<>();

        for(Book book : books){
            ListOfAllBookResponseDto responseDto = new ListOfAllBookResponseDto();
            responseDto.setTitle(book.getTitle());
            responseDto.setPrice(book.getPrice());
            responseDto.setGenre(book.getGenre());
            responseDto.setAuthorName(book.getAuthor().getName());
            responseDtos.add(responseDto);

        }

        return responseDtos;
    }

    public lastUpdateDateAndStatusRespondDto lastUpdateDateAndStatus(int cardId) throws Exception {
        LibraryCard card = libraryCardRepository.findById(cardId).get();
        if(card == null)throw new Exception("Enter a valid card Id!");

        lastUpdateDateAndStatusRespondDto lastUpdateDateAndStatusRespondDto= new lastUpdateDateAndStatusRespondDto();
        lastUpdateDateAndStatusRespondDto.setDate(card.getUpdationDate().toString());
        lastUpdateDateAndStatusRespondDto.setStatus(card.getCardStatus().toString());

        return lastUpdateDateAndStatusRespondDto;
    }
}
