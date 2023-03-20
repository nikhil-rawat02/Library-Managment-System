package com.LibraryManagment.System.Exception;

public class CardNotActivatedException extends Exception{
    public CardNotActivatedException(String errorMessage){
        super(errorMessage);
    }
}
