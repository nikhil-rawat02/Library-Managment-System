package com.LibraryManagment.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class IssueResponseDto {

    private String transactionId;
    private String bookTitle;
    private String authorName;
    private String operation; // issue or return
    private String transactionStatus;

}
