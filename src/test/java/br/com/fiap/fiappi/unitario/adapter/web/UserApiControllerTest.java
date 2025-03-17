package br.com.fiap.fiappi.unitario.adapter.web;

import br.com.fiap.fiappi.adapter.web.UserApiController;
import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
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
    void shouldCreateUser() {
        CreateUserDto dto = new CreateUserDto("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                RoleName.ROLE_ADMINISTRATOR,
                "address");

        userApiController.createUser(dto);

        verify(userController, times(1)).createUser(any());

    }

    @Test
    void shouldListUsers() {

        userApiController.listUsers(0, 10);

        verify(userController, times(1)).findAll(any(), any());

    }

    @Test
    void shouldFindUserById() {

        userApiController.findById(UUID.randomUUID());

        verify(userController, times(1)).findById(any());

    }

    @Test
    void shouldUpdateUser() {

        UpdateUserDto dto = new UpdateUserDto("name2",
                "email@email.com.br",
                "email_login");

        userApiController.updateUser(UUID.randomUUID(), dto);

        verify(userController, times(1)).updateUser(any(), any());

    }

    @Test
    void shouldChangeUserPassword() {

        ChangeUserPasswordDto dto = new ChangeUserPasswordDto("passwordNew123$");

        userApiController.changePassword(UUID.randomUUID(), dto);

        verify(userController, times(1)).changeUserPassword(any(), any());

    }

    @Test
    void shouldDeleteUser() {

        userApiController.deleteUser(UUID.randomUUID());

        verify(userController, times(1)).deleteUser(any());

    }

    @Test
    void shouldUpdateRoleUser() {
        UpdateRoleUserDTO dto = new UpdateRoleUserDTO(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        userApiController.updateRule(dto);

        verify(userController, times(1)).updateRoleUser(any());
    }


}