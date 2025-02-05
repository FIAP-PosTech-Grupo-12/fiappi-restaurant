package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.user.domain.model.User;

import java.util.UUID;

public interface FindByIdRestaurantUseCase {
    RestaurantDTO findById(UUID id);
}
