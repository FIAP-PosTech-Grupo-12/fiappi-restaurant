package br.com.fiap.fiappi.core.menu.usecase;

import java.util.UUID;

public interface CreateMenuUseCase {
    void create(byte[] bytes, String dto, UUID userRequestId);
}
