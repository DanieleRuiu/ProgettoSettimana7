package it.epicode.PrenotazioneEvento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    //metodo EntityNotFoundException
    public String getDetails(){
        return "Entity Not Found";
    }
}