package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;

public interface CreateRestaurantUseCase {
    void create(RestaurantDTO restaurantDTO);
}
