package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.UpdateRestaurantUseCaseImpl;
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

    @InjectMocks
    UpdateRestaurantUseCaseImpl updateRestaurant;

    @Mock
    RestaurantGateway restaurantGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
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
