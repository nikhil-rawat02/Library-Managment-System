package com.LibraryManagment.System.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailUpdatedResponseDto {
    private int id;

    private String name;

    private String email;
}
