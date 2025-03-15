package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.restaurant.controller.RestaurantController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantMenuDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/restaurant")
public class RestaurantApiController {

    private final RestaurantController restaurantController;

    public RestaurantApiController(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RestaurantDTO restaurantDTO,
                                       @AuthenticationPrincipal User userRequest) {
        restaurantController.create(restaurantDTO, userRequest.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantMenuDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(restaurantController.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantMenuDTO>> listRestaurants(@PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) @ParameterObject Pageable pageable) {
        return ResponseEntity.ok(restaurantController.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
        restaurantController.deleteRestaurant(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    public ResponseEntity<Void> updateRestaurant(@RequestBody RestaurantDTO dto,
                                                 @AuthenticationPrincipal User userRequest) {
        restaurantController.updateRestaurant(dto, userRequest.getId());
        return ResponseEntity.ok().build();
    }

}
