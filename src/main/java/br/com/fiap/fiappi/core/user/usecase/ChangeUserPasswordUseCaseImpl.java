package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChangeUserPasswordUseCaseImpl implements ChangeUserPasswordUseCase {

    private final UserGateway userGateway;

    @Override
    public void changePassword(UUID id, ChangeUserPasswordDto dto) {
        userGateway.changePassword(id, dto);
    }
}
