package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.DeleteRestaurantUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeleteRestaurantUseCaseTest {

    AutoCloseable mock;

    @InjectMocks
    DeleteRestaurantUseCaseImpl delete;

    @Mock
    RestaurantGateway restaurantGateway;

    @Mock
    ImageGateway imageGateway;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldDeleteRestaurant(){

        doNothing().when(imageGateway).deleteByPath(any());

        delete.delete(UUID.randomUUID());

        verify(restaurantGateway, times(1)).delete(any());

    }

}
