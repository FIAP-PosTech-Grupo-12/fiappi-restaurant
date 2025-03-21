package br.com.fiap.fiappi.config.exception;

import br.com.fiap.fiappi.config.security.exception.UserNotFoundException;
import br.com.fiap.fiappi.core.menu.exception.ImageConverterException;
import br.com.fiap.fiappi.core.menu.exception.MenuConverterException;
import br.com.fiap.fiappi.core.menu.exception.MenuNotFoundException;
import br.com.fiap.fiappi.core.menu.exception.ValidationMenuException;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import br.com.fiap.fiappi.core.user.exception.UserLoginAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Object>  handlerRestaurantNotFoundException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object>  handlerUserNotFoundException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(UserLoginAlreadyExistsException.class)
    public ResponseEntity<Object>  handlerUserAlreadyExistsException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(MenuNotFoundException.class)
    public ResponseEntity<Object> handlerMenuNotFoundException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(MenuConverterException.class)
    public ResponseEntity<Object> handlerMenuConverterException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(ImageConverterException.class)
    public ResponseEntity<Object> handlerImageConverterException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(ValidationMenuException.class)
    public ResponseEntity<Object> handlerValidationMenuException(RuntimeException ex, WebRequest request){
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
