package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;

import java.util.List;
import java.util.UUID;

public interface FindMenuByIdRestaurantUseCase {
    List<MenuDTO> findByIdRestaurant(UUID idRestaurant);
}
