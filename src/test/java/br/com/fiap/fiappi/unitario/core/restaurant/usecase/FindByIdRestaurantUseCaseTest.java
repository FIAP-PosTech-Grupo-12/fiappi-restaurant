package br.com.fiap.fiappi.unitario.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import br.com.fiap.fiappi.core.restaurant.usecase.FindByIdRestaurantUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FindByIdRestaurantUseCaseTest {

    AutoCloseable mock;

    @InjectMocks
    FindByIdRestaurantUseCaseImpl findById;

    @Mock
    RestaurantGateway restaurantGateway;

    private final UUID restaurantId = UUID.randomUUID();

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldFindRestaurantByValidId(){

        RestaurantMenuDTO restaurantMenuDTO = new RestaurantMenuDTO(
                restaurantId,
                "RestauranteTeste",
                "Rua Teste, num. 123",
                KitchenTypeEnum.TRADITIONAL,
                "7-22",
                UUID.randomUUID(),
                List.of()
        );
        when(restaurantGateway.findBy(restaurantId)).thenReturn(restaurantMenuDTO);

        assertEquals(restaurantMenuDTO, findById.findById(restaurantId));

        verify(restaurantGateway, times(1)).findBy(restaurantId);
    }

    @Test
    void shouldNotFindRestaurantByInvalidId(){

        UUID id = UUID.randomUUID();
        when(restaurantGateway.findBy(id)).thenThrow(new RestaurantNotFoundException("Restaurant not found"));

        assertThrows(RestaurantNotFoundException.class, () -> findById.findById(id));
    }

    @Test
    void shouldNotFindRestaurantByNullId() {

        when(restaurantGateway.findBy(null)).thenThrow(new RestaurantNotFoundException("Restaurant not found"));

        assertThrows(RestaurantNotFoundException.class, () -> findById.findById(null));
    }

}
