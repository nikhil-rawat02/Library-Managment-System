package com.LibraryManagment.System.DTO;

import com.LibraryManagment.System.Enum.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDto {

    private String name;

    private int age;

    private String email;

    private Department department;

}
