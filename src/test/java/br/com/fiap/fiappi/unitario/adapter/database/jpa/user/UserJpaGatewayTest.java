package br.com.fiap.fiappi.unitario.adapter.database.jpa.user;

import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.adapter.database.jpa.user.UserJpaGateway;
import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.config.security.service.AuthenticationService;
import br.com.fiap.fiappi.core.user.domain.User;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserJpaGatewayTest {

    AutoCloseable mock;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private UserJpaGateway userJpaGateway;

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

        when(passwordEncoder.encode(any())).thenReturn("123");

        userJpaGateway.create(new User("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR));

        verify(userRepository, times(1)).save(any());

    }

    @Test
    void shouldListUsers() {

        when(authenticationService.isAuthenticatedUserAdmin()).thenReturn(true);

        userJpaGateway.findAll(0, 10);

        verify(userRepository, times(1)).findAllOrLoginNameUser(any(), any(), any());

    }

    @Test
    void shouldUpdateUser() {

        br.com.fiap.fiappi.adapter.database.jpa.user.entity.User user = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(
                "name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR
        );

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(authenticationService.isAuthenticatedUserAdmin()).thenReturn(true);

        userJpaGateway.update(new User("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR));

        verify(userRepository, times(1)).saveAndFlush(any());

    }

    @Test
    void shouldChangeUserPassword() {

        br.com.fiap.fiappi.adapter.database.jpa.user.entity.User user = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(
                "name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR
        );

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(authenticationService.isAuthenticatedUserAdmin()).thenReturn(true);

        ChangeUserPasswordDto dto = new ChangeUserPasswordDto("passwordNew123$");

        userJpaGateway.changePassword(UUID.randomUUID(), dto);

        verify(userRepository, times(1)).saveAndFlush(any());

    }

    @Test
    void shouldDeleteUser() {
        br.com.fiap.fiappi.adapter.database.jpa.user.entity.User user = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(
                "name",
                "email@email.com.br",
                "email_login",
                "password#123",
                LocalDateTime.now(),
                "address",
                RoleName.ROLE_ADMINISTRATOR
        );

        when(userRepository.findById(any())).thenReturn(Optional.of(user));

        when(authenticationService.isAuthenticatedUserAdmin()).thenReturn(true);

        userJpaGateway.delete(UUID.randomUUID());

        verify(userRepository, times(1)).delete(any());

    }

    @Test
    void shouldUpdateRoleUser() {

        userJpaGateway.updateRule(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        verify(userRepository, times(1)).updateRoleById(any(), any());
    }

}