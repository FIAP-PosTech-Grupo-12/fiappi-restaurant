package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserUpdateRuleUseCaseImpl implements UserUpdateRuleUseCase{

    private final UserGateway userGateway;

    @Override
    public void updateRoleUser(UpdateRoleUserDTO dto) {

        UUID id = dto.id();
        RoleName roleName = dto.roleName();

        userGateway.updateRule(id, roleName);

    }
}
