package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Enum.TransactionStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class getAllTnxForCardResponseDto {

    private String transactionNumber;

    private Date transactionDate;

    private String Operation;

    private TransactionStatus transactionStatus;

    private String message;
}
