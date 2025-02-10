package br.com.fiap.fiappi.adapter.database.jpa.restaurant;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestauranteEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
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
    public void create(Restaurant restaurante) {
        RestauranteEntity restauranteEntity = new RestauranteEntity(
                restaurante.getName(),
                restaurante.getAddress(),
                restaurante.getKitchenType(),
                restaurante.getOpeningHours(),
                restaurante.getOwnerId(),
                restaurante.getCreatorId(),
                restaurante.getCreatedAt(),
                restaurante.getUpdatedBy(),
                restaurante.getUpdatedAt()
        );

        restaurantRepository.save(restauranteEntity);
    }

    @Override
    public RestaurantMenuDTO findBy(UUID id) {
        RestauranteEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

        return new RestaurantMenuDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getKitchenType(),
                restaurant.getOpeningHours(),
                restaurant.getOwnerId(),
                restaurant.getMenus().stream()
                        .map(m -> new MenuDTO(
                                restaurant.getId(),
                                m.getName(),
                                m.getDescription(),
                                m.getPrice(),
                                m.getAvailableInRestaurantOnly()
                        ))
                        .toList()
        );
    }

    @Override
    public List<RestaurantMenuDTO> findAll(Pageable pageable) {

        Page<RestauranteEntity> restaurants = restaurantRepository.findAll(pageable);


        return restaurants.stream()
                .map(r -> new RestaurantMenuDTO(r.getId(),
                        r.getName(),
                        r.getAddress(),
                        r.getKitchenType(),
                        r.getOpeningHours(),
                        r.getOwnerId(),
                        r.getMenus().stream()
                                .map(m -> new MenuDTO(
                                                r.getId(),
                                                m.getName(),
                                                m.getDescription(),
                                                m.getPrice(),
                                                m.getAvailableInRestaurantOnly()
                                ))
                                .toList()))
                .toList();

    }

    @Override
    public void delete(UUID id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public void update(Restaurant restaurante) {
        RestauranteEntity restauranteEntity = new RestauranteEntity(
                restaurante.getId(),
                restaurante.getName(),
                restaurante.getAddress(),
                restaurante.getKitchenType(),
                restaurante.getOpeningHours(),
                restaurante.getOwnerId(),
                restaurante.getCreatorId(),
                restaurante.getCreatedAt(),
                restaurante.getUpdatedBy(),
                restaurante.getUpdatedAt()
        );

        restaurantRepository.save(restauranteEntity);

    }
}
