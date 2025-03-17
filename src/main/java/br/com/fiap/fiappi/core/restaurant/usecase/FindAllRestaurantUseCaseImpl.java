package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindAllRestaurantUseCaseImpl implements FindAllRestaurantUseCase{

    private final RestaurantGateway restaurantGateway;


    @Override
    public List<RestaurantMenuDTO> findAll(Pageable pageable) {
        return restaurantGateway.findAll(pageable);
    }
}
