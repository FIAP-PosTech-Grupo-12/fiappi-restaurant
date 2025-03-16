package br.com.fiap.fiappi.core.menu.usecase;

import java.util.List;
import java.util.UUID;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;

public interface FindMenuByIdRestaurantUseCase {
    List<MenuDTO> findByIdRestaurant(UUID idRestaurant);
}
