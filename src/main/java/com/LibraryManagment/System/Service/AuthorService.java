package com.LibraryManagment.System.Service;

import com.LibraryManagment.System.DTO.AuthorRequestDto;
import com.LibraryManagment.System.DTO.AuthorResponseDto;
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
        List<Author> authors;
        List<GetAllAuthorsResponseDto> getAllAuthorsResponseDtos = new ArrayList<>();
        try{
            authors= authorRepository.findAll();

            for(Author author: authors){
                GetAllAuthorsResponseDto getAllAuthorsResponseDto = new GetAllAuthorsResponseDto();
                getAllAuthorsResponseDto.setName(author.getName());
                getAllAuthorsResponseDto.setAge(author.getAge());
                getAllAuthorsResponseDto.setRating(author.getRating());
                getAllAuthorsResponseDto.setEmail(author.getEmail());
                getAllAuthorsResponseDto.setBooks(author.getBooks());
                getAllAuthorsResponseDtos.add(getAllAuthorsResponseDto);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return getAllAuthorsResponseDtos;
    }
}
