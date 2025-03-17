package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.domain.User;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void update(UpdateUserDto updateUserDto, UUID userRequestId) {

        User user = new User(userRequestId,
                updateUserDto.name(),
                updateUserDto.email(),
                updateUserDto.address());

        userGateway.update(user);
    }
}
