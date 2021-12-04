package com.nubank.exam.usecases.exceptions;

public class CardNotActiveException extends ValidationException {
    public CardNotActiveException() {
        super("card-not-active");
    }
}
