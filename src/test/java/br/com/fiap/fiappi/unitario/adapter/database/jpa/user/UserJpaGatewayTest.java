package br.com.fiap.fiappi.unitario.adapter.database.jpa.user;

import br.com.fiap.fiappi.adapter.database.jpa.user.UserJpaGateway;
import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.core.user.enums.RoleName;
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

class UserJpaGatewayTest {

    AutoCloseable mock;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserJpaGateway restaurantJpaGateway;

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

        restaurantJpaGateway.updateRule(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        verify(userRepository, times(1)).updateRoleById(any(), any());
    }

}