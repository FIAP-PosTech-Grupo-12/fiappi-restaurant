package br.com.fiap.fiappi.user.adapter.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record UpdateUserDto(
        @Schema
        String name,
        @Schema
        String email,
        @Schema
        String address
) { }
