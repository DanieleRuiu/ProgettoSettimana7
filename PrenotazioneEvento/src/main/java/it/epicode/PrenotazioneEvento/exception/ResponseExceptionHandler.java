package it.epicode.PrenotazioneEvento.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import it.epicode.PrenotazioneEvento.exception.ErrorResponse;
import it.epicode.PrenotazioneEvento.exception.NotFoundException;
import it.epicode.PrenotazioneEvento.exception.BadRequestException;
import it.epicode.PrenotazioneEvento.exception.UnAuthorizedException;



@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler(it.epicode.PrenotazioneEvento.exception.NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException e){
        return new it.epicode.PrenotazioneEvento.exception.ErrorResponse(e.getMessage(), "Internal Server Error");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public it.epicode.PrenotazioneEvento.exception.ErrorResponse ExceptionHandler(Exception e){
        return new it.epicode.PrenotazioneEvento.exception.ErrorResponse(e.getMessage(), "Internal Server Error");
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse badRequestExceptionHandler(BadRequestException e){
        return new ErrorResponse(e.getMessage(), e.getDetails());
    }

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponse unAuthorizedExceptionHandler(UnAuthorizedException e){
        return new ErrorResponse(e.getMessage(), "Internal Server Error");
    }


}



















//
//
//import org.springframework.data.crossstore.ChangeSetPersister;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class ResponseExceptionHandler {
//
//    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
//    public ResponseEntity<ErrorResponse> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler(LoginFaultException.class)
//    public ResponseEntity<ErrorResponse> handleLoginFaultException(LoginFaultException ex) {
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED.value(), System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
//    }
//
//    // Gestisci altre eccezioni qui...
//
//    @ExceptionHandler(UnAuthorizedException.class)
//    public ResponseEntity<ErrorResponse> handleUnAuthorizedException(UnAuthorizedException ex) {
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.FORBIDDEN.value(), System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis());
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
