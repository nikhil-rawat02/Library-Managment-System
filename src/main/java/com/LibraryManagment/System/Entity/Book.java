package com.LibraryManagment.System.Entity;

import com.LibraryManagment.System.Enum.Genre;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private int price;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    private boolean isIssued;

    private Date returnDate;

    @ManyToOne
    @JoinColumn
    Author author;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    List<Transaction> listOfTransaction = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    LibraryCard card;


}
