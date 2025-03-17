package br.com.fiap.fiappi.core.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginUserDto(
        @Schema
        String login,
        @Schema
        String password
) {
}
