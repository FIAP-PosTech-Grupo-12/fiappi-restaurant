package br.com.fiap.fiappi.core.restaurant.usecase;

import java.util.UUID;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;

public interface UpdateRestaurantUseCase {
    void update(RestaurantDTO dto, UUID userRequestId);
}
