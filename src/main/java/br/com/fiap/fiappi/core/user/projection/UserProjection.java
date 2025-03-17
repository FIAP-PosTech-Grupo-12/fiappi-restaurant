package br.com.fiap.fiappi.core.user.projection;


import br.com.fiap.fiappi.core.user.enums.RoleName;

import java.util.UUID;

public interface UserProjection {

    UUID getId();

    String getName();

    String getLogin();

    RoleName getRole();

}
