package it.epicode.PrenotazioneEvento.exception;




public class LoginFaultException extends RuntimeException{

    public LoginFaultException(String message){
        super(message);
    }
}