package br.com.fiap.fiappi.unitario.core.user.controller;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.usecase.UserUpdateRuleUseCase;
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

class UserControllerTest {

    AutoCloseable mock;

    @Mock
    private UserUpdateRuleUseCase updateRestaurantUseCase;

    @InjectMocks
    private UserController restaurantController;

    private final UUID restaurantId = UUID.randomUUID();
    private final UUID userRequestId = UUID.randomUUID();


    @BeforeEach
    void setUp() {
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldUpdateRoleUser() {
        UpdateRoleUserDTO dto = new UpdateRoleUserDTO(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        restaurantController.updateRoleUser(dto);

        verify(updateRestaurantUseCase, times(1)).updateRoleUser(any());
    }


}