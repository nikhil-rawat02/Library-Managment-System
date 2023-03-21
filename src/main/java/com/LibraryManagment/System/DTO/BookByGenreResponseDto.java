package com.LibraryManagment.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookByGenreResponseDto {

    private int id;

    private String title;

    private int price;

    String authorName;

}
