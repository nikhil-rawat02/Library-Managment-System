package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.DTO.AuthorRequestDto;
import com.LibraryManagment.System.DTO.AuthorResponseDto;
import com.LibraryManagment.System.DTO.GetAllAuthorsResponseDto;
import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public AuthorResponseDto addAuthor( @RequestBody AuthorRequestDto authorRequestDto){
        return authorService.addAuthor(authorRequestDto);
    }

//    Error: aborted check
//    @GetMapping("/getAuthor")
//    public List<GetAllAuthorsResponseDto> getAuthors(){
//        return authorService.getAuthors();
//    }
}
