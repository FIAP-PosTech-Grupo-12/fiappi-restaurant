package br.com.fiap.fiappi.core.menu.gateway;

import br.com.fiap.fiappi.core.menu.domain.Menu;

import java.util.UUID;

public interface MenuGateway {
    void create(Menu menu);

    String findPathByID(UUID id);

    void deleteById(UUID id);
}
