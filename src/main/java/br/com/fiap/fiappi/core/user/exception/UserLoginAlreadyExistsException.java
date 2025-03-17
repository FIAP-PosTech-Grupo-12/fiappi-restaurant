package br.com.fiap.fiappi.core.user.exception;

public class UserLoginAlreadyExistsException extends RuntimeException{

    public UserLoginAlreadyExistsException(String message) {
        super(message);
    }

}
