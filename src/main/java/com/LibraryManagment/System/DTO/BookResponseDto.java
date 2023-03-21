package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Entity.Author;
import com.LibraryManagment.System.Entity.LibraryCard;
import com.LibraryManagment.System.Entity.Transaction;
import com.LibraryManagment.System.Enum.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookResponseDto {

    private int id;

    private String title;

    private int price;

    private String genre;

    private String isIssued;

    private Date returnDate;

    String authorName;

    String studentId;
}
