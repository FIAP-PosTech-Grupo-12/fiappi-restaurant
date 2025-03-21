package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindByIdRestaurantUseCaseImpl implements FindByIdRestaurantUseCase{

    private final RestaurantGateway restaurantGateway;


    @Override
    public RestaurantMenuDTO findById(UUID id) {
        return restaurantGateway.findBy(id);
    }
}
