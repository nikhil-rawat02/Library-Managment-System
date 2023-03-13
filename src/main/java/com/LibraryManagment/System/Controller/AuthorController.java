package com.LibraryManagment.System.Controller;

import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    @PostMapping("/add")
    public String addAuthor(Author author){
        authorService.addAuthor(author);
        return "Author added";
    }

    @GetMapping("/getAuthor")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }
}
