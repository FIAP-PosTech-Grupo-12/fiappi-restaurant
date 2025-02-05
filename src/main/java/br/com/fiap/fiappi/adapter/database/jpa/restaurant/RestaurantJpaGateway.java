package br.com.fiap.fiappi.adapter.database.jpa.restaurant;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestauranteEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
import br.com.fiap.fiappi.user.domain.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantJpaGateway implements RestauranteGateway {

    private final RestaurantRepository restaurantRepository;

    public RestaurantJpaGateway(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    @Transactional
    public void create(Restaurant restaurante, User userRequest) {

        RestauranteEntity restauranteEntity = new RestauranteEntity(
                restaurante.getName(),
                restaurante.getAddress(),
                restaurante.getKitchenType(),
                restaurante.getOpeningHours(),
                restaurante.getOwnerId(),
                userRequest.getId(),
                restaurante.getCreatedAt(),
                restaurante.getUpdatedBy(),
                restaurante.getUpdatedAt()
        );

        restaurantRepository.save(restauranteEntity);

    }

    @Override
    public RestaurantDTO findBy(UUID id) {
        RestauranteEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getKitchenType(),
                restaurant.getOpeningHours(),
                restaurant.getOwnerId());
    }

    @Override
    public List<RestaurantDTO> findAll(Pageable pageable) {

        Page<RestauranteEntity> restaurants = restaurantRepository.findAll(pageable);

        return restaurants.stream()
                .map(r -> new RestaurantDTO(r.getId(), r.getName(), r.getAddress(), r.getKitchenType(), r.getOpeningHours(), r.getOwnerId()))
                .toList();

    }
}
