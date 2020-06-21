package com.lucas.kalah.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class GameValidationException extends GameException{

    public GameValidationException(String message){
        super(message);
    }

}
