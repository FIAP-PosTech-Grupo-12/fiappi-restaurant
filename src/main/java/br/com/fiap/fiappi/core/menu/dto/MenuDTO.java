package br.com.fiap.fiappi.core.menu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuDTO {
    private UUID menuId;
    private UUID restaurantId;
    private String name;
    private String description;
    private Double price;
    private Boolean availableInRestaurantOnly;
    private byte[] bytes;

    public MenuDTO(UUID restaurantId, String name, String description, Double price, Boolean availableInRestaurantOnly) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.availableInRestaurantOnly = availableInRestaurantOnly;
    }


}
