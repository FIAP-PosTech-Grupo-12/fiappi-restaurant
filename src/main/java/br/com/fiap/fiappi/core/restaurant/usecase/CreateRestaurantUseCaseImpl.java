package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateRestaurantUseCaseImpl implements CreateRestaurantUseCase{

    private final RestaurantGateway restaurantGateway;

    @Override
    public void create(RestaurantDTO restaurantDTO, UUID userRequestId) {

        Restaurant restaurante = new Restaurant(restaurantDTO.name(),
                restaurantDTO.address(), restaurantDTO.kitchenType(),
                restaurantDTO.openingHours(), restaurantDTO.ownerId(), userRequestId,
                LocalDateTime.now(), userRequestId, LocalDateTime.now());

        restaurantGateway.create(restaurante);


    }
}
