package br.com.fiap.fiappi.unitario.core.restaurant.controller;

import br.com.fiap.fiappi.core.restaurant.controller.RestaurantController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.usecase.*;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    AutoCloseable mock;

    @Mock
    private CreateRestaurantUseCase createRestaurantUseCase;

    @Mock
    private FindByIdRestaurantUseCase findByIdRestaurantUseCase;

    @Mock
    private FindAllRestaurantUseCase findAllRestaurantUseCase;

    @Mock
    private DeleteRestaurantUseCase deleteRestaurantUseCase;

    @Mock
    private UpdateRestaurantUseCase updateRestaurantUseCase;

    @InjectMocks
    private RestaurantController restaurantController;

    private final UUID restaurantId = UUID.randomUUID();
    private final UUID userRequestId = UUID.randomUUID();

    private final RestaurantDTO restaurantDTO = new RestaurantDTO(
            restaurantId,
            "RestauranteTeste",
            "Rua Teste, num. 123",
            KitchenTypeEnum.TRADITIONAL,
            "7-22",
            UUID.randomUUID()
    );

    private final RestaurantMenuDTO restaurantMenuDTO = new RestaurantMenuDTO(
            restaurantDTO.id(),
            restaurantDTO.name(),
            restaurantDTO.address(),
            restaurantDTO.kitchenType(),
            restaurantDTO.openingHours(),
            restaurantDTO.ownerId(),
            List.of()
    );

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCreateRestaurant() {

        doNothing().when(createRestaurantUseCase).create(restaurantDTO, userRequestId);

        assertDoesNotThrow(() -> restaurantController.create(restaurantDTO, userRequestId));
        verify(createRestaurantUseCase, times(1)).create(restaurantDTO, userRequestId);
    }

    @Test
    void shouldFindRestaurantById() {

        when(findByIdRestaurantUseCase.findById(restaurantId)).thenReturn(restaurantMenuDTO);

        RestaurantMenuDTO foundRestaurant = restaurantController.findById(restaurantId);

        assertNotNull(foundRestaurant);
        assertEquals(restaurantId, foundRestaurant.id());
        verify(findByIdRestaurantUseCase, times(1)).findById(restaurantId);
    }

    @Test
    void shouldFindAllRestaurants() {

        Pageable pageable = PageRequest.of(0, 10);
        List<RestaurantMenuDTO> restaurantList = List.of(restaurantMenuDTO);

        when(findAllRestaurantUseCase.findAll(pageable)).thenReturn(restaurantList);
        List<RestaurantMenuDTO> restaurantListFound = restaurantController.findAll(pageable);

        assertNotNull(restaurantListFound);
        assertFalse(restaurantListFound.isEmpty());
        assertEquals(restaurantList, restaurantListFound);
        verify(findAllRestaurantUseCase, times(1)).findAll(pageable);
    }

    @Test
    void shouldDeleteRestaurant() {

        doNothing().when(deleteRestaurantUseCase).delete(restaurantId);

        assertDoesNotThrow(() -> restaurantController.deleteRestaurant(restaurantId));
        verify(deleteRestaurantUseCase, times(1)).delete(restaurantId);
    }

    @Test
    void shouldUpdateRestaurant() {

        doNothing().when(updateRestaurantUseCase).update(restaurantDTO, userRequestId);

        assertDoesNotThrow(() -> restaurantController.updateRestaurant(restaurantDTO, userRequestId));
        verify(updateRestaurantUseCase, times(1)).update(restaurantDTO, userRequestId);
    }
}