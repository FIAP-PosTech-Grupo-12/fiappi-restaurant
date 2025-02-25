package br.com.fiap.fiappi.unitario.adapter.database.jpa.restaurant;

import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.RestaurantJpaGateway;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestaurantEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.config.security.exception.UserNotFoundException;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RestaurantJpaGatewayTest {

    AutoCloseable mock;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RestaurantJpaGateway restaurantJpaGateway;

    private final UUID ownerId = UUID.randomUUID();
    private final UUID creatorId = UUID.randomUUID();
    private final UUID restaurantId = UUID.randomUUID();

    private final Restaurant restaurant = new Restaurant(
            restaurantId,
            "RestauranteTeste",
            "Rua Teste, num. 123",
            KitchenTypeEnum.TRADITIONAL,
            "7-22",
            ownerId,
            creatorId,
            LocalDateTime.now(),
            creatorId,
            LocalDateTime.now()
    );

    private final RestaurantEntity restaurantEntity = new RestaurantEntity(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getKitchenType(),
            restaurant.getOpeningHours(),
            restaurant.getOwnerId(),
            restaurant.getCreatorId(),
            restaurant.getCreatedAt(),
            restaurant.getUpdatedBy(),
            restaurant.getUpdatedAt()
    );

    private final RestaurantMenuDTO restaurantMenuDTO = new RestaurantMenuDTO(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getKitchenType(),
            restaurant.getOpeningHours(),
            restaurant.getOwnerId(),
            new ArrayList<>());

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCreateRestaurantWithValidData() {

        when(userRepository.findById(ownerId)).thenReturn(Optional.of(new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User()));
        when(restaurantRepository.save(restaurantEntity)).thenReturn(restaurantEntity);

        assertDoesNotThrow(() -> restaurantJpaGateway.create(restaurant));

        verify(restaurantRepository, times(1)).save(any(RestaurantEntity.class));
    }

    @Test
    void shouldNotCreateRestaurantWithNonExistentOwnerId() {

        when(userRepository.findById(ownerId)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> restaurantJpaGateway.create(restaurant));
    }

    @Test
    void shouldFindRestaurantByValidId() {

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));

        assertEquals(restaurantMenuDTO, restaurantJpaGateway.findBy(restaurantId));
    }

    @Test
    void shouldNotFindRestaurantByInvalidId() {

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantJpaGateway.findBy(restaurantId));
    }

    @Test
    void shouldFindAllRestaurants() {

        Pageable pageable = PageRequest.of(0, 10);
        Page<RestaurantEntity> restaurantEntityPage = new PageImpl<>(List.of(restaurantEntity), pageable, 1);

        when(restaurantRepository.findAll(pageable)).thenReturn(restaurantEntityPage);

        assertEquals(List.of(restaurantMenuDTO), restaurantJpaGateway.findAll(pageable));
    }

    @Test
    void shouldDeleteRestaurant() {

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));
        doNothing().when(restaurantRepository).deleteById(restaurantId);

        restaurantJpaGateway.delete(restaurantId);

        verify(restaurantRepository, times(1)).deleteById(restaurantId);
    }

    @Test
    void shouldUpdateRestaurant() {

        when(restaurantRepository.save(any(RestaurantEntity.class))).thenReturn(new RestaurantEntity());
        restaurantJpaGateway.update(restaurant);

        verify(restaurantRepository, times(1)).save(restaurantEntity);
    }

    @Test
    void shouldFindPathsImagesByValidId() {

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));

        assertEquals(restaurantEntity.getMenus().stream().map(MenuEntity::getPhotoPath).toList(),
                restaurantJpaGateway.findPathsImagesByIdRestaurant(restaurantId));
    }

    @Test
    void shouldNotFindPathsImagesByInvalidId() {

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> restaurantJpaGateway.findPathsImagesByIdRestaurant(restaurantId));
    }
}