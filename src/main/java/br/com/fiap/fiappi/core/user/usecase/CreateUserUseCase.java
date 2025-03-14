package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.CreateUserDto;

public interface CreateUserUseCase {
    void create(CreateUserDto dto);
}
