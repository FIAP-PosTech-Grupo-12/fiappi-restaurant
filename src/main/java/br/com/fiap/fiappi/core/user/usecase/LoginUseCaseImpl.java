package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginUseCaseImpl implements LoginUseCase {

    private final UserGateway userGateway;

    @Override
    public TokenUserDto login(LoginUserDto dto) {
        return userGateway.login(dto);
    }
}
