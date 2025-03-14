package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.domain.User;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void create(CreateUserDto dto) {
        User user = new User(dto.name(),
                dto.email(),
                dto.login(),
                dto.password(),
                LocalDateTime.now(),
                dto.address(),
                dto.role());

        userGateway.create(user);
    }
}
