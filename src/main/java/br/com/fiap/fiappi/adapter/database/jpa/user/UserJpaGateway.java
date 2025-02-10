package br.com.fiap.fiappi.adapter.database.jpa.user;

import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public void updateRule(UUID id, RoleName roleName) {

        userRepository.updateRoleById(roleName, id);

    }
}
