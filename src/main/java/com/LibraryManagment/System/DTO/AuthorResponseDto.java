package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorResponseDto {

    private String name;

    private int age;

    private double rating;

    private String email;

    private List<Book> books;

}
