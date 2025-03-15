package br.com.fiap.fiappi.unitario.adapter.database.jpa.menu;

import br.com.fiap.fiappi.adapter.database.jpa.menu.MenuJpaGateway;
import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.adapter.database.jpa.menu.repository.MenuRepository;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestaurantEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.core.menu.domain.Menu;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.exception.MenuNotFoundException;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class MenuJpaGatewayTest {

    AutoCloseable mocks;

    @Mock
    private MenuRepository menuRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private MenuJpaGateway menuJpaGateway;

    private final UUID restaurantId = UUID.randomUUID();
    private final UUID menuId = UUID.randomUUID();
    private final UUID creatorId = UUID.randomUUID();

    private Menu createDummyMenu() {
        return new Menu(
                menuId,
                new br.com.fiap.fiappi.core.restaurant.domain.Restaurant(restaurantId, "Test Restaurant",
                        "Test Address",
                        br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum.TRADITIONAL, "7-22", creatorId,
                        creatorId,
                        LocalDateTime.now(), creatorId, LocalDateTime.now()),
                "Test Menu",
                "Test Description",
                12.50,
                true,
                "/test/photo",
                creatorId,
                LocalDateTime.now(),
                creatorId,
                LocalDateTime.now());
    }

    private MenuEntity createDummyMenuEntity(Menu menu) {
        return new MenuEntity(
                menu.getId(),
                new RestaurantEntity(menu.getRestaurant().getId()),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getAvailableInRestaurantOnly(),
                menu.getPhotoPath(),
                menu.getCreatorId(),
                menu.getCreatedAt(),
                menu.getUpdatedBy(),
                menu.getUpdatedAt());
    }

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void shouldCreateMenuSuccessfully() {
        Menu menu = createDummyMenu();
        MenuEntity expectedEntity = createDummyMenuEntity(menu);

        when(restaurantRepository.findById(menu.getRestaurant().getId()))
                .thenReturn(Optional.of(new RestaurantEntity(menu.getRestaurant().getId())));
        when(menuRepository.save(any(MenuEntity.class))).thenReturn(expectedEntity);

        assertDoesNotThrow(() -> menuJpaGateway.create(menu));

        verify(restaurantRepository, times(1)).findById(menu.getRestaurant().getId());
        verify(menuRepository, times(1)).save(any(MenuEntity.class));
    }

    @Test
    void shouldThrowRestaurantNotFoundExceptionOnCreate() {
        Menu menu = createDummyMenu();
        when(restaurantRepository.findById(menu.getRestaurant().getId()))
                .thenReturn(Optional.empty());

        assertThrows(RestaurantNotFoundException.class, () -> menuJpaGateway.create(menu));
        verify(restaurantRepository, times(1)).findById(menu.getRestaurant().getId());
        verify(menuRepository, never()).save(any(MenuEntity.class));
    }

    @Test
    void shouldReturnPhotoPathById() {
        Menu menu = createDummyMenu();
        MenuEntity menuEntity = createDummyMenuEntity(menu);

        when(menuRepository.findById(menu.getId())).thenReturn(Optional.of(menuEntity));

        String path = menuJpaGateway.findPathByID(menu.getId());

        assertEquals(menuEntity.getPhotoPath(), path);
        verify(menuRepository, times(1)).findById(menu.getId());
    }

    @Test
    void shouldThrowMenuNotFoundExceptionWhenFindingPathById() {
        UUID unknownMenuId = UUID.randomUUID();
        when(menuRepository.findById(unknownMenuId)).thenReturn(Optional.empty());

        assertThrows(MenuNotFoundException.class, () -> menuJpaGateway.findPathByID(unknownMenuId));
        verify(menuRepository, times(1)).findById(unknownMenuId);
    }

    @Test
    void shouldDeleteMenuById() {
        UUID idToDelete = UUID.randomUUID();
        doNothing().when(menuRepository).deleteById(idToDelete);

        menuJpaGateway.deleteById(idToDelete);

        verify(menuRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    void shouldFindMenusByRestaurantId() {
        Menu menu = createDummyMenu();
        MenuEntity menuEntity = createDummyMenuEntity(menu);
        List<MenuEntity> menuEntities = Collections.singletonList(menuEntity);

        when(menuRepository.findByRestaurant_Id(restaurantId)).thenReturn(menuEntities);

        Map<MenuDTO, String> result = menuJpaGateway.findByIdRestaurant(restaurantId);

        MenuDTO expectedDTO = new MenuDTO(
                menuEntity.getId(),
                restaurantId,
                menuEntity.getName(),
                menuEntity.getDescription(),
                menuEntity.getPrice(),
                menuEntity.getAvailableInRestaurantOnly(),
                null);

        assertTrue(result.containsKey(expectedDTO));
        assertEquals(menuEntity.getPhotoPath(), result.get(expectedDTO));

        verify(menuRepository, times(1)).findByRestaurant_Id(restaurantId);
    }

    @Test
    void shouldUpdateMenuSuccessfully() {
        Menu menu = createDummyMenu();
        MenuEntity expectedEntity = createDummyMenuEntity(menu);

        when(menuRepository.save(any(MenuEntity.class))).thenReturn(expectedEntity);

        assertDoesNotThrow(() -> menuJpaGateway.update(menu));
        verify(menuRepository, times(1)).save(argThat(entity -> entity.getId().equals(menu.getId()) &&
                entity.getName().equals(menu.getName()) &&
                entity.getDescription().equals(menu.getDescription()) &&
                entity.getPrice().equals(menu.getPrice())));
    }
}