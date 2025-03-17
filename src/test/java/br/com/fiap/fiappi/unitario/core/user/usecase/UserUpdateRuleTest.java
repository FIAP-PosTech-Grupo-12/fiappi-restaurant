package br.com.fiap.fiappi.unitario.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.usecase.UserUpdateRuleUseCaseImpl;
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

public class UserUpdateRuleTest {

    AutoCloseable mock;

    @InjectMocks
    UserUpdateRuleUseCaseImpl userUpdateRule;

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
    void shouldUpdateUser(){

        UpdateRoleUserDTO dto = new UpdateRoleUserDTO(UUID.randomUUID(), RoleName.ROLE_CUSTOMER);

        userUpdateRule.updateRoleUser(dto);

        verify(userGateway, times(1)).updateRule(any(), any());

    }

}
