package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindByIdUserUseCaseImpl implements FindByIdUserUseCase {

    private final UserGateway userGateway;

    @Override
    public UserDetailedProjection findById(UUID id) {
        return userGateway.findBy(id);
    }
}
