package com.LibraryManagment.System.Exception;

public class BookAlreadyIssuedException extends Exception{
    public BookAlreadyIssuedException(String errorMessage){
        super(errorMessage);
    }
}
