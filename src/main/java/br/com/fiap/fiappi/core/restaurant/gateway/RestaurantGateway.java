package br.com.fiap.fiappi.core.restaurant.gateway;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface RestaurantGateway {
    void create(Restaurant restaurante);

    RestaurantMenuDTO findBy(UUID id);

    List<RestaurantMenuDTO> findAll(Pageable pageable);

    void delete(UUID id);

    void update(Restaurant restaurante);
}
