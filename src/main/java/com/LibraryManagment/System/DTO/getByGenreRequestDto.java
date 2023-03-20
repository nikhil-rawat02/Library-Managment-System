package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Enum.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class getByGenreRequestDto {
    private Genre genre;
}
