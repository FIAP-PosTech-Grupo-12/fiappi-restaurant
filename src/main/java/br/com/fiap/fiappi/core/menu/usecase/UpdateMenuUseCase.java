package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;

import java.util.UUID;

public interface UpdateMenuUseCase {
    void update(MenuDTO dto, UUID userRequestId);
}
