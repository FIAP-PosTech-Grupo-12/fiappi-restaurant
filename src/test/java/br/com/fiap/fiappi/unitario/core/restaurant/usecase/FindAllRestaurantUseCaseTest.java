package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.FindAllRestaurantUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindAllRestaurantUseCaseTest {

    AutoCloseable mock;

    @InjectMocks
    FindAllRestaurantUseCaseImpl findAll;

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
    void shouldFindAllRestaurants(){

        List<RestaurantMenuDTO> restaurantMenuDTO = List.of(new RestaurantMenuDTO(
                UUID.randomUUID(),
                "RestauranteTeste",
                "Rua Teste, num. 123",
                KitchenTypeEnum.TRADITIONAL,
                "7-22",
                UUID.randomUUID(),
                List.of()
        ));
        Pageable pageable = PageRequest.of(0, 10);
        when(restaurantGateway.findAll(pageable)).thenReturn(restaurantMenuDTO);

        assertEquals(restaurantMenuDTO, findAll.findAll(pageable));

        verify(restaurantGateway, times(1)).findAll(pageable);
    }

}
