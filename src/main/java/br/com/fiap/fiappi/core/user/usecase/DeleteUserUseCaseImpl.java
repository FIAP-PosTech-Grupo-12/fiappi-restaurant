package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserGateway userGateway;

    @Override
    public void delete(UUID id) {
        userGateway.delete(id);
    }
}
