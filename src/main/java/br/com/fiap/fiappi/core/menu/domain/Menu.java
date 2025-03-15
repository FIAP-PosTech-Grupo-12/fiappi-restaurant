package br.com.fiap.fiappi.core.menu.domain;

import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Menu {
    private UUID id;
    private Restaurant restaurant;
    private String name;
    private String description;
    private Double price;
    private Boolean availableInRestaurantOnly;
    private String photoPath;
    private UUID creatorId;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;


    public Menu(Restaurant restaurant, String name, String description, Double price, Boolean availableInRestaurantOnly, String photoPath, UUID creatorId, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
        this.restaurant = restaurant;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableInRestaurantOnly = availableInRestaurantOnly;
        this.photoPath = photoPath;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }
}
