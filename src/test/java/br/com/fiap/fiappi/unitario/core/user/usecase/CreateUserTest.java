package br.com.fiap.fiappi.unitario.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.usecase.CreateUserUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CreateUserTest {

    AutoCloseable mock;

    @InjectMocks
    CreateUserUseCaseImpl createUserUseCase;

    @Mock
    UserGateway userGateway;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mock.close();
    }

    @Test
    void shouldCreateUserUser(){

        CreateUserDto dto = new CreateUserDto("name",
                "email@email.com.br",
                "email_login",
                "password#123",
                RoleName.ROLE_ADMINISTRATOR,
                "address");

        createUserUseCase.create(dto);

        verify(userGateway, times(1)).create(any());

    }

}
