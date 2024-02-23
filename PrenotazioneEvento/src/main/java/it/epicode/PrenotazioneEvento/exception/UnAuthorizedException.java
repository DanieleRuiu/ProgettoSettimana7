package it.epicode.PrenotazioneEvento.exception;




public class UnAuthorizedException extends RuntimeException{
    public UnAuthorizedException(String message){
        super(message);
    }
}
