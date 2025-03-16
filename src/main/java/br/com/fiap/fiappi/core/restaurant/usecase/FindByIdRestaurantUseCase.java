package br.com.fiap.fiappi.core.restaurant.usecase;

import java.util.UUID;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;

public interface FindByIdRestaurantUseCase {
    RestaurantMenuDTO findById(UUID id);
}
