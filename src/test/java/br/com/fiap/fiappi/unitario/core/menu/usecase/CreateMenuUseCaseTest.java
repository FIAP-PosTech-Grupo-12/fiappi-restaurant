package br.com.fiap.fiappi.unitario.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.menu.usecase.CreateMenuUseCaseImpl;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCreateMenu(){

        MenuDTO dto = new MenuDTO(UUID.randomUUID(), "nameMenu", "Description", 10.00, true);

        Gson gson = new Gson();

        String dtoString = gson.toJson(dto);

        byte[] bytes = new byte[0];

        when(imageGateway.create(any(), any())).thenReturn("/path");

        createMenu.create(bytes, dtoString, UUID.randomUUID());

        verify(imageGateway, times(1)).create(any(), any());
        verify(menuGateway, times(1)).create(any());

    }

}
