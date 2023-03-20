package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.lastUpdateDateAndStatusRespondDto;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Enum.cardStatus;
import com.LibraryManagment.System.Repository.LibraryCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LibraryCardService {
    @Autowired
    LibraryCardRepository libraryCardRepository;
    public List<Book> getAllBook(int cardNo) {
        LibraryCard card = libraryCardRepository.findById(cardNo).get();
        return card.getBookIssued();
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
