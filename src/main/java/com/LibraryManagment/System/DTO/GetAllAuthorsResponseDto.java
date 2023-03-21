package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Entity.Book;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAuthorsResponseDto {

    private String name;

    private int age;

    private double rating;

    private String email;

    List<BookResponseDto> books;
}
