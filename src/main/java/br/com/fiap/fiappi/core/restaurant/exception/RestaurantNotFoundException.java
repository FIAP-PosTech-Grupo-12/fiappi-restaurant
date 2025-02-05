package br.com.fiap.fiappi.core.restaurant.exception;

public class RestaurantNotFoundException extends RuntimeException{

    public RestaurantNotFoundException(String msg){
        super(msg);
    }

}
