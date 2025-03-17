package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;

import java.util.UUID;

public interface UpdateUserUseCase {
    void update(UpdateUserDto dto, UUID userRequestId);
}
