package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;

import java.util.UUID;

public interface FindByIdRestaurantUseCase {
    RestaurantMenuDTO findById(UUID id);
}
