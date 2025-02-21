package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.DeleteRestaurantUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteRestaurantUseCaseTest {

    @InjectMocks
    DeleteRestaurantUseCaseImpl delete;

    @Mock
    RestaurantGateway restaurantGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteRestaurant(){

        delete.delete(UUID.randomUUID());

        verify(restaurantGateway, times(1)).delete(any());

    }

}
