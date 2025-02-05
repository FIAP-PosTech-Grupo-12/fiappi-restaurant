package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.gateway.RestauranteGateway;
import br.com.fiap.fiappi.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindAllRestaurantUseCaseImpl implements FindAllRestaurantUseCase{

    private final RestauranteGateway restauranteGateway;


    @Override
    public List<RestaurantDTO> findAll(Pageable pageable) {
        return restauranteGateway.findAll(pageable);
    }
}
