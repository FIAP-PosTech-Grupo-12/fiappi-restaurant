package br.com.fiap.fiappi.core.restaurant.controller;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import br.com.fiap.fiappi.core.restaurant.usecase.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final FindByIdRestaurantUseCase findByIdRestaurantUseCase;
    private final FindAllRestaurantUseCase findAllRestaurantsUseCase;
    private final DeleteRestaurantUseCase deleteRestaurantUseCase;
    private final UpdateRestaurantUseCase updateRestaurantUseCase;

    public void create(RestaurantDTO restaurantDTO, UUID userRequestId) {
        createRestaurantUseCase.create(restaurantDTO, userRequestId);
    }

    public RestaurantMenuDTO findById(UUID id) {
        return findByIdRestaurantUseCase.findById(id);
    }

    public List<RestaurantMenuDTO> findAll(Pageable pageable) {
        return findAllRestaurantsUseCase.findAll(pageable);

    }

    public void deleteRestaurant(UUID id) {
        deleteRestaurantUseCase.delete(id);

    }

    public void updateRestaurant(RestaurantDTO dto, UUID userRequestId) {
        updateRestaurantUseCase.update(dto, userRequestId);
    }
}
