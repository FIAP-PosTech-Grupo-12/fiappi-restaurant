package br.com.fiap.fiappi.unitario.adapter.web;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.adapter.web.RestaurantApiController;
import br.com.fiap.fiappi.core.restaurant.controller.RestaurantController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantApiControllerTest {

    AutoCloseable mock;

    @Mock
    private RestaurantController restaurantController;

    @InjectMocks
    private RestaurantApiController restaurantApiController;

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

    private final User user = new User(
            userRequestId,
            "Usuario",
            "usuario@email.com",
            "usuario",
            "teste",
            LocalDateTime.now(),
            "Rua Qualquer num 423",
            RoleName.ROLE_ADMINISTRATOR
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
        doNothing().when(restaurantController).create(any(RestaurantDTO.class), any(UUID.class));

        ResponseEntity<Void> response = restaurantApiController.create(restaurantDTO, user);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(restaurantController, times(1)).create(any(RestaurantDTO.class), any(UUID.class));
    }

    @Test
    void shouldFindRestaurantById() {
        when(restaurantController.findById(restaurantId)).thenReturn(restaurantMenuDTO);

        ResponseEntity<RestaurantMenuDTO> response = restaurantApiController.findById(restaurantId);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantMenuDTO, response.getBody());
        verify(restaurantController, times(1)).findById(restaurantId);
    }

    @Test
    void shouldListRestaurants() {
        Pageable pageable = PageRequest.of(0, 10);
        List<RestaurantMenuDTO> restaurantList = List.of(restaurantMenuDTO);

        when(restaurantController.findAll(pageable)).thenReturn(restaurantList);

        ResponseEntity<List<RestaurantMenuDTO>> response = restaurantApiController.listRestaurants(pageable);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(restaurantList, response.getBody());
        verify(restaurantController, times(1)).findAll(pageable);
    }

    @Test
    void shouldDeleteRestaurant() {
        doNothing().when(restaurantController).deleteRestaurant(restaurantId);

        ResponseEntity<Void> response = restaurantApiController.deleteRestaurant(restaurantId);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(restaurantController, times(1)).deleteRestaurant(restaurantId);
    }

    @Test
    void shouldUpdateRestaurant() {
        doNothing().when(restaurantController).updateRestaurant(any(RestaurantDTO.class), any(UUID.class));

        ResponseEntity<Void> response = restaurantApiController.updateRestaurant(restaurantDTO, user);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(restaurantController, times(1)).updateRestaurant(any(RestaurantDTO.class), any(UUID.class));
    }
}