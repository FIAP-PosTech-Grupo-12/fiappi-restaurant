package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;

public interface LoginUseCase {
    TokenUserDto login(LoginUserDto dto);
}
