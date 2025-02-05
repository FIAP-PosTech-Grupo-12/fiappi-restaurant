package br.com.fiap.fiappi.core.restaurant.gateway;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.user.domain.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RestauranteGateway {
    void create(Restaurant restaurante, User userRequest);

    RestaurantDTO findBy(UUID id);

    List<RestaurantDTO> findAll(Pageable pageable);
}
