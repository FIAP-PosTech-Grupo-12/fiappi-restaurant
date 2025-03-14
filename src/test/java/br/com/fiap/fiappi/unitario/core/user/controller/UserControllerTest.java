package br.com.fiap.fiappi.unitario.core.user.controller;

import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.usecase.*;
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
    @Mock
    private CreateUserUseCase createUserUseCase;
    @Mock
    private LoginUseCase loginUseCase;
    @Mock
    private FindAllUsersUseCase findAllUsersUseCase;
    @Mock
    private FindByIdUserUseCase findByIdUserUseCase;
    @Mock
    private UpdateUserUseCase updateUserUseCase;
    @Mock
    private ChangeUserPasswordUseCase changeUserPasswordUseCase;
    @Mock
    private DeleteUserUseCase deleteUserUseCase;

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
    void shouldCreateUser() {
        CreateUserDto dto = new CreateUserDto("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                RoleName.ROLE_ADMINISTRATOR,
                "address");

        userController.createUser(dto);

        verify(createUserUseCase, times(1)).create(any());

    }

    @Test
    void shouldListUsers() {

        userController.findAll(0, 10);

        verify(findAllUsersUseCase, times(1)).findAll(any(), any());

    }

    @Test
    void shouldFindUserById() {

        userController.findById(UUID.randomUUID());

        verify(findByIdUserUseCase, times(1)).findById(any());

    }

    @Test
    void shouldUpdateUser() {

        UpdateUserDto dto = new UpdateUserDto("name2",
                "email@email.com.br",
                "email_login");

        userController.updateUser(UUID.randomUUID(), dto);

        verify(updateUserUseCase, times(1)).update(any(), any());

    }

    @Test
    void shouldChangeUserPassword() {

        ChangeUserPasswordDto dto = new ChangeUserPasswordDto("passwordNew123$");

        userController.changeUserPassword(UUID.randomUUID(), dto);

        verify(changeUserPasswordUseCase, times(1)).changePassword(any(), any());

    }

    @Test
    void shouldDeleteUser() {

        userController.deleteUser(UUID.randomUUID());

        verify(deleteUserUseCase, times(1)).delete(any());

    }

    @Test
    void shouldUpdateRoleUser() {
        UpdateRoleUserDTO dto = new UpdateRoleUserDTO(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        userController.updateRoleUser(dto);

        verify(updateRestaurantUseCase, times(1)).updateRoleUser(any());
    }


}