package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;

public interface CreateRestaurantUseCase {
    void create(RestaurantDTO restaurantDTO, User userRequest);
}
