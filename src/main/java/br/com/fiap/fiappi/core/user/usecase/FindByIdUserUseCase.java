package br.com.fiap.fiappi.core.user.usecase;

import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;

import java.util.UUID;

public interface FindByIdUserUseCase {
    UserDetailedProjection findById(UUID id);
}
