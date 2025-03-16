package br.com.fiap.fiappi.core.restaurant.usecase;

import java.util.List;

import org.springframework.data.domain.Pageable;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;

public interface FindAllRestaurantUseCase {
    List<RestaurantMenuDTO> findAll(Pageable pageable);
}
