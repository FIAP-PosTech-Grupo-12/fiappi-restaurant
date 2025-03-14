package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.projection.UserProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindAllUsersUseCaseImpl implements FindAllUsersUseCase {

    private final UserGateway userGateway;

    @Override
    public List<UserProjection> findAll(Integer page, Integer size) {
        return userGateway.findAll(page, size);
    }
}
