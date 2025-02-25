package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.UpdateRestaurantUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class UpdateRestaurantUseCaseTest {

    AutoCloseable mock;

    @InjectMocks
    UpdateRestaurantUseCaseImpl updateRestaurant;

    @Mock
    RestaurantGateway restaurantGateway;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldUpdateRestaurant(){

        RestaurantDTO restaurantDTO = new RestaurantDTO(UUID.randomUUID(),
                "Name",
                "Address",
                KitchenTypeEnum.FAST_FOOD,
                "7-22",
                UUID.randomUUID());

        updateRestaurant.update(restaurantDTO, UUID.randomUUID());

        verify(restaurantGateway, times(1)).update(any());
    }

}
