package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;

import java.util.UUID;

public interface ChangeUserPasswordUseCase {
    void changePassword(UUID id, ChangeUserPasswordDto dto);
}
