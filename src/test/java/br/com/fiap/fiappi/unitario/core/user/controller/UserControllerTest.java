package br.com.fiap.fiappi.unitario.core.user.controller;

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

import java.util.UUID;

import static org.mockito.Mockito.*;

class UserControllerTest {

    AutoCloseable mock;

    @Mock
    private UserUpdateRuleUseCase updateRestaurantUseCase;

    @InjectMocks
    private UserController userController;

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

        userController.updateRoleUser(dto);

        verify(updateRestaurantUseCase, times(1)).updateRoleUser(any());
    }


}