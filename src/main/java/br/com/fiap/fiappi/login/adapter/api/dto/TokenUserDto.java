package br.com.fiap.fiappi.login.adapter.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record TokenUserDto(
        @Schema
        String accessToken,
        @Schema
        String refreshToken
) {
}
