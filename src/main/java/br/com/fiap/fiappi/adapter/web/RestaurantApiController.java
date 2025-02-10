package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.core.restaurant.controller.RestaurantController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("restaurant")
public class RestaurantApiController {

    private final RestaurantController restaurantController;

    public RestaurantApiController(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        restaurantController.create(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(restaurantController.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDTO>> listRestaurants(@PageableDefault(sort = "updatedAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(restaurantController.findAll(pageable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        restaurantController.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
