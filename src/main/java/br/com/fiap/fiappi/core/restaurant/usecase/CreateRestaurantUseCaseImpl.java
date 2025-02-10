package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase{

    private final RestauranteGateway restauranteGateway;

    @Override
    public void create(RestaurantDTO restaurantDTO) {

        Restaurant restaurante = new Restaurant(restaurantDTO.name(),
                restaurantDTO.address(), restaurantDTO.kitchenType(),
                restaurantDTO.openingHours(), restaurantDTO.ownerId(), restaurantDTO.creatorId(),
                LocalDateTime.now(), restaurantDTO.updatedById(), LocalDateTime.now());

        restauranteGateway.create(restaurante);


    }
}
