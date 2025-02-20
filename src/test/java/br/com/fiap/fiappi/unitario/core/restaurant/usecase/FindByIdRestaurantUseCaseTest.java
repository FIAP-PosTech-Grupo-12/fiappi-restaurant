package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.FindByIdRestaurantUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class FindByIdRestaurantUseCaseTest {

    @InjectMocks
    FindByIdRestaurantUseCaseImpl findById;

    @Mock
    RestauranteGateway restauranteGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindRestaurantById(){

        findById.findById(UUID.randomUUID());

        verify(restauranteGateway, times(1)).findBy(any());

    }

}
