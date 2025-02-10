package br.com.fiap.fiappi.core.user.gateway;

import br.com.fiap.fiappi.core.user.enums.RoleName;

import java.util.UUID;

public interface UserGateway {

    void updateRule(UUID id, RoleName roleName);
}
