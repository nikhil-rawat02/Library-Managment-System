package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Enum.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListOfAllBookResponseDto {

    private String title;

    private int price;

    private Genre genre;

    private String authorName;

}
