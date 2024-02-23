package it.epicode.PrenotazioneEvento.security;

public class CustomException extends RuntimeException {


    public CustomException(String message){
        super(message);
    }
}
