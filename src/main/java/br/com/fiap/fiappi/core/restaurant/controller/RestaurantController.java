package br.com.fiap.fiappi.core.restaurant.controller;

import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.usecase.CreateRestaurantUseCase;
import br.com.fiap.fiappi.core.restaurant.usecase.FindAllRestaurantUseCase;
import br.com.fiap.fiappi.core.restaurant.usecase.FindByIdRestaurantUseCase;
import br.com.fiap.fiappi.user.domain.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class RestaurantController {

    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final FindByIdRestaurantUseCase findByIdRestaurantUseCase;
    private final FindAllRestaurantUseCase findAllRestaurantsUseCase;


    public RestaurantController(CreateRestaurantUseCase createRestaurantUseCase, FindByIdRestaurantUseCase findByIdRestaurantUseCase, FindAllRestaurantUseCase findAllRestaurantsUseCase) {
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.findByIdRestaurantUseCase = findByIdRestaurantUseCase;
        this.findAllRestaurantsUseCase = findAllRestaurantsUseCase;
    }

    public void create(RestaurantDTO restaurantDTO, User userRequest) {
        createRestaurantUseCase.create(restaurantDTO, userRequest);
    }

    public RestaurantDTO findById(UUID id) {
        return findByIdRestaurantUseCase.findById(id);
    }

    public List<RestaurantDTO> findAll(Pageable pageable) {
        return findAllRestaurantsUseCase.findAll(pageable);

    }
}
