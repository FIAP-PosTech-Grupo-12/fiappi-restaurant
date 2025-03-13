package br.com.fiap.fiappi.unitario.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.menu.usecase.CreateMenuUseCaseImpl;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CreateMenuUseCaseTest {

    @InjectMocks
    CreateMenuUseCaseImpl createMenu;

    @Mock
    MenuGateway menuGateway;

    @Mock
    ImageGateway imageGateway;

    @Mock
    RestaurantGateway restaurantGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMenu(){
        UUID idRestaurant = UUID.randomUUID();

        byte[] bytes = new byte[0];

        when(imageGateway.create(any(), any())).thenReturn("/path");
        when(restaurantGateway.findBy(idRestaurant)).thenReturn(new RestaurantMenuDTO(idRestaurant, "name", "address", KitchenTypeEnum.FAST_FOOD, "7-22", UUID.randomUUID(), new ArrayList<>()));

        createMenu.create(bytes, idRestaurant.toString(), "nameMenu", "description", "10.00", "true", UUID.randomUUID());

        verify(imageGateway, times(1)).create(any(), any());
        verify(menuGateway, times(1)).create(any());

    }

}
