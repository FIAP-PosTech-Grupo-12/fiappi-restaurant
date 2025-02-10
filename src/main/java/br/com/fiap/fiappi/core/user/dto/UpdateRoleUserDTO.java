package br.com.fiap.fiappi.core.user.dto;

import br.com.fiap.fiappi.core.user.enums.RoleName;

import java.util.UUID;

public record UpdateRoleUserDTO(
        UUID id,
        RoleName roleName

) {
}
