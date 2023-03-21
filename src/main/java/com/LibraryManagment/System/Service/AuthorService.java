package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.AuthorRequestDto;
import com.LibraryManagment.System.DTO.AuthorResponseDto;
import com.LibraryManagment.System.DTO.BookResponseDto;
import com.LibraryManagment.System.DTO.GetAllAuthorsResponseDto;
import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Entity.Book;
import com.LibraryManagment.System.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public AuthorResponseDto addAuthor(AuthorRequestDto authorRequestDto) {
        Author author = new Author();
        System.out.println(authorRequestDto.getName().toString());
        author.setName(authorRequestDto.getName());
        author.setAge(authorRequestDto.getAge());
        author.setEmail(authorRequestDto.getEmail());
        author.setRating(authorRequestDto.getRating());
        authorRepository.save(author);

        AuthorResponseDto authorResponseDto = new AuthorResponseDto();
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setEmail(author.getEmail());
        authorResponseDto.setRating(authorRequestDto.getRating());
        authorResponseDto.setBooks(author.getBooks());

        return  authorResponseDto;
    }

    public List<GetAllAuthorsResponseDto> getAuthors()  {
        List<Integer> authorIds = new ArrayList<>();
        List<GetAllAuthorsResponseDto> getAllAuthorsResponseDtos = new ArrayList<>();
        try {
            authorIds = authorRepository.findAllAuthors();
        }catch (Exception e){
            e.printStackTrace();
        }
        for(Integer authorId: authorIds){
            Author author = authorRepository.findById(authorId).get();

            GetAllAuthorsResponseDto getAllAuthorsResponseDto = new GetAllAuthorsResponseDto();

            getAllAuthorsResponseDto.setName(author.getName());
            getAllAuthorsResponseDto.setAge(author.getAge());
            getAllAuthorsResponseDto.setRating(author.getRating());
            getAllAuthorsResponseDto.setEmail(author.getEmail());

            List<Book> books = author.getBooks();
            List<BookResponseDto> bookResponseDto = new ArrayList<>();
            for(Book book: books){
                BookResponseDto bookResponseDto1 = new BookResponseDto();
                bookResponseDto1.setTitle(book.getTitle());
                bookResponseDto1.setId(book.getId());
                bookResponseDto1.setPrice(book.getPrice());
                bookResponseDto1.setGenre(book.getGenre().toString());
                if(book.isIssued()){
                    bookResponseDto1.setIsIssued("Yes Issued");
                    bookResponseDto1.setStudentId(book.getCard().getStudent().getName());
                    bookResponseDto1.setReturnDate(book.getReturnDate());
                }else{
                    bookResponseDto1.setIsIssued("Not Issued");
                    bookResponseDto1.setStudentId(null);
                    bookResponseDto1.setReturnDate(null);
                }
                bookResponseDto.add(bookResponseDto1);
            }
            getAllAuthorsResponseDto.setBooks(bookResponseDto);

            getAllAuthorsResponseDtos.add(getAllAuthorsResponseDto);

        }

        return getAllAuthorsResponseDtos;
    }
}
