package com.LibraryManagment.System.Exception;

public class StudentNotFoundException extends Exception{
    public StudentNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
