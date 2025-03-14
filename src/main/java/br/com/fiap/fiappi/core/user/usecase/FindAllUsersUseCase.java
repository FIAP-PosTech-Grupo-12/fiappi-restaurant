package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.projection.UserProjection;

import java.util.List;

public interface FindAllUsersUseCase {
    List<UserProjection> findAll(Integer page, Integer size);
}
