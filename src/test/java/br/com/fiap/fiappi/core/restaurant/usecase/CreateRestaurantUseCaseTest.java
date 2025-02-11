package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
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

public class CreateRestaurantUseCaseTest {

    @InjectMocks
    CreateRestaurantUseCaseImpl createRestaurantUseCase;

    @Mock
    RestauranteGateway restauranteGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateRestaurant(){

        RestaurantDTO restaurantDTO = new RestaurantDTO(UUID.randomUUID(),
                "Name",
                "Address",
                KitchenTypeEnum.FAST_FOOD,
                "7-22",
                UUID.randomUUID());

        createRestaurantUseCase.create(restaurantDTO, UUID.randomUUID());

        verify(restauranteGateway, times(1)).create(any());

    }

}
