package com.LibraryManagment.System.Entity;

import com.LibraryManagment.System.Enum.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionNumber;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    private boolean isIssueOperation;

    @CreationTimestamp
    private Date transactionDate;

    private String message;

    @ManyToOne
    @JoinColumn
    LibraryCard card;

    @ManyToOne
    @JoinColumn
    Book book;

}
