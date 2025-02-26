package br.com.fiap.fiappi.unitario.core.menu.controller;       
import br.com.fiap.fiappi.core.menu.controller.MenuController;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.usecase.CreateMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.DeleteMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.FindMenuByIdRestaurantUseCase;
import br.com.fiap.fiappi.core.menu.usecase.UpdateMenuUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuControllerTest {

    @Mock
    private CreateMenuUseCase createMenuUseCase;

    @Mock
    private DeleteMenuUseCase deleteMenuUseCase;

    @Mock
    private FindMenuByIdRestaurantUseCase findMenuByIdRestaurantUseCase;

    @Mock
    private UpdateMenuUseCase updateMenuUseCase;

    @InjectMocks
    private MenuController menuController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        byte[] bytes = "test bytes".getBytes();
        String dto = "{\"name\":\"Test Menu\"}";
        UUID userRequestId = UUID.randomUUID();

        menuController.create(bytes, dto, userRequestId);

        verify(createMenuUseCase, times(1)).create(bytes, dto, userRequestId);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        menuController.delete(id);

        verify(deleteMenuUseCase, times(1)).delete(id);
    }

    @Test
    void testFindByIdRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        MenuDTO menuDTO = new MenuDTO(restaurantId, "Test Menu", "Test Description", 10.0, true);
        List<MenuDTO> expected = Collections.singletonList(menuDTO);

        when(findMenuByIdRestaurantUseCase.findByIdRestaurant(restaurantId)).thenReturn(expected);

        List<MenuDTO> result = menuController.findByIdRestaurant(restaurantId);

        assertEquals(expected, result);
        verify(findMenuByIdRestaurantUseCase, times(1)).findByIdRestaurant(restaurantId);
    }

    @Test
    void testUpdateMenu() {
        MenuDTO menuDTO = new MenuDTO(UUID.randomUUID(), "Test Menu", "Test Description", 10.0, true);
        UUID userRequestId = UUID.randomUUID();

        menuController.updateMenu(menuDTO, userRequestId);

        verify(updateMenuUseCase, times(1)).update(menuDTO, userRequestId);
    }
}
