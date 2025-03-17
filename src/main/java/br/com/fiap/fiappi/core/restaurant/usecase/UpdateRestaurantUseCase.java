package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;

import java.util.UUID;

public interface UpdateRestaurantUseCase {
    void update(RestaurantDTO dto, UUID userRequestId);
}
