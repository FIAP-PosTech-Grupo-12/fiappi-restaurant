package br.com.fiap.fiappi.unitario.core.user.usecase;

import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.usecase.DeleteUserUseCaseImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DeleteUserTest {

    AutoCloseable mock;

    @InjectMocks
    DeleteUserUseCaseImpl deleteUser;

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

        deleteUser.delete(UUID.randomUUID());

        verify(userGateway, times(1)).delete(any());

    }

}
