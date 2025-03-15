package br.com.fiap.fiappi.unitario.adapter.web;

import br.com.fiap.fiappi.adapter.web.MenuApiController;
import br.com.fiap.fiappi.core.menu.controller.MenuController;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuApiControllerTest {

    AutoCloseable mock;

    @Mock
    private MenuController menuController;

    @InjectMocks
    private MenuApiController menuApiController;

    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void testCreate() throws IOException {
        UUID userRequestId = UUID.randomUUID();
        UUID idRestaurant = UUID.randomUUID();

        br.com.fiap.fiappi.adapter.database.jpa.user.entity.User user = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(
                userRequestId,
                "name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR
        );

        MockMultipartFile file = new MockMultipartFile("file", "menu.txt", MediaType.TEXT_PLAIN_VALUE,
                "dummy content".getBytes());

        menuApiController.create(file, idRestaurant.toString(), "nameMenu", "description", "10.00", "true", user);

        verify(menuController, times(1)).create(file.getBytes(), idRestaurant.toString(), "nameMenu", "description", "10.00", "true", userRequestId);
    }

    @Test
    void testDelete() {
        UUID id = UUID.randomUUID();

        menuApiController.delete(id);

        verify(menuController, times(1)).delete(id);
    }

    @Test
    void testFindByIdRestaurant() {
        UUID restaurantId = UUID.randomUUID();
        MenuDTO menuDTO = new MenuDTO(restaurantId, "Test Menu", "Test Description", 10.0, true);
        List<MenuDTO> expected = Collections.singletonList(menuDTO);

        when(menuController.findByIdRestaurant(restaurantId)).thenReturn(expected);

        ResponseEntity<List<MenuDTO>> result = menuApiController.findByIdRestaurant(restaurantId);

        assertEquals(expected, result.getBody());
        verify(menuController, times(1)).findByIdRestaurant(restaurantId);
    }

    @Test
    void testUpdateMenu() {
        MenuDTO menuDTO = new MenuDTO(UUID.randomUUID(), "Test Menu", "Test Description", 10.0, true);
        UUID userRequestId = UUID.randomUUID();
        br.com.fiap.fiappi.adapter.database.jpa.user.entity.User user = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(
                userRequestId,
                "name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR
        );

        menuApiController.updateMenu(menuDTO, user);

        verify(menuController, times(1)).updateMenu(menuDTO, userRequestId);
    }
}