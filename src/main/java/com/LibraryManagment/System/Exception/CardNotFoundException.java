package com.LibraryManagment.System.Exception;

public class CardNotFoundException extends Exception{
    public CardNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
