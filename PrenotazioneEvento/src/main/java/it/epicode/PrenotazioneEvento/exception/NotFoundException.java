package it.epicode.PrenotazioneEvento.exception;





public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}
