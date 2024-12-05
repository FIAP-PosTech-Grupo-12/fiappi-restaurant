package br.com.fiap.fiappi.user.adapter.api.projection;

import br.com.fiap.fiappi.user.domain.enums.RoleName;

import java.util.UUID;

public interface UserProjection {

    UUID getId();

    String getName();

    String getLogin();

    RoleName getRole();

}
