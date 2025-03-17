package br.com.fiap.fiappi.core.restaurant.usecase;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FindAllRestaurantUseCase {
    List<RestaurantMenuDTO> findAll(Pageable pageable);
}
