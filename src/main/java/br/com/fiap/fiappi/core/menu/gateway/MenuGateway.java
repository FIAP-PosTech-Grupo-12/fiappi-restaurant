package br.com.fiap.fiappi.core.menu.gateway;

import java.util.Map;
import java.util.UUID;

import br.com.fiap.fiappi.core.menu.domain.Menu;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;

public interface MenuGateway {
    void create(Menu menu);

    String findPathByID(UUID id);

    void deleteById(UUID id);

    Map<MenuDTO, String> findByIdRestaurant(UUID idRestaurant);

    void update(Menu menu);
}
