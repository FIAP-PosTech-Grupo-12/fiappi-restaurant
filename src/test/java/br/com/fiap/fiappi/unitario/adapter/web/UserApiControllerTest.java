package br.com.fiap.fiappi.unitario.adapter.web;

import br.com.fiap.fiappi.adapter.web.UserApiController;
import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.Mockito.*;

class UserApiControllerTest {

    AutoCloseable mock;

    @Mock
    private UserController userController;

    @InjectMocks
    private UserApiController userApiController;

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

        userApiController.updateRule(dto);

        verify(userController, times(1)).updateRoleUser(any());
    }


}