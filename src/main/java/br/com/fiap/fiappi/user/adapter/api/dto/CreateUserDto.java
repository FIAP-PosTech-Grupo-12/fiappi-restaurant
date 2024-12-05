package br.com.fiap.fiappi.user.adapter.api.dto;

import br.com.fiap.fiappi.user.domain.enums.RoleName;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateUserDto(
        @Schema
        String name,
        @Schema
        String email,
        @Schema
        String login,
        @Schema
        String password,
        @Schema
        RoleName role,
        @Schema
        String address
) { }
