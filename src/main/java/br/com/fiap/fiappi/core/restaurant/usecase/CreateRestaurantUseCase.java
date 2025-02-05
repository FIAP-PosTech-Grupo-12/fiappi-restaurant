package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.user.domain.model.User;

public interface CreateRestaurantUseCase {
    void create(RestaurantDTO restaurantDTO, User userRequest);
}
