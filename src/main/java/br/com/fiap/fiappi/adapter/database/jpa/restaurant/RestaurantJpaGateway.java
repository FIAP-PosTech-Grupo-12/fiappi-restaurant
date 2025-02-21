package br.com.fiap.fiappi.adapter.database.jpa.restaurant;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestaurantEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository.RestaurantRepository;
import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.config.security.exception.UserNotFoundException;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RestaurantJpaGateway implements RestaurantGateway {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public RestaurantJpaGateway(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void create(Restaurant restaurant) {

        userRepository.findById(restaurant.getOwnerId()).orElseThrow(() -> new UserNotFoundException("Owner id user not found"));

        RestaurantEntity restaurantEntity = new RestaurantEntity(
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getKitchenType(),
                restaurant.getOpeningHours(),
                restaurant.getOwnerId(),
                restaurant.getCreatorId(),
                restaurant.getCreatedAt(),
                restaurant.getUpdatedBy(),
                restaurant.getUpdatedAt()
        );

        restaurantRepository.save(restaurantEntity);
    }

    @Override
    public RestaurantMenuDTO findBy(UUID id) {
        RestaurantEntity restaurant = restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException("Restaurant not found"));

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

        Page<RestaurantEntity> restaurants = restaurantRepository.findAll(pageable);


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
        RestaurantEntity restaurantEntity = new RestaurantEntity(
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

        restaurantRepository.save(restaurantEntity);

    }
}
