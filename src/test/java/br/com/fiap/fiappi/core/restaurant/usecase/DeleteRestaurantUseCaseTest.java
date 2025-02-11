package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
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
    RestauranteGateway restauranteGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteRestaurant(){

        delete.delete(UUID.randomUUID());

        verify(restauranteGateway, times(1)).delete(any());

    }

}
