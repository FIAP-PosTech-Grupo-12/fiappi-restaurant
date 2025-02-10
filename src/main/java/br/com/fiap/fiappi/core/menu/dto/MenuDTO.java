package br.com.fiap.fiappi.core.menu.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class MenuDTO {
    private UUID restaurantId;
    private String name;
    private String description;
    private Double price;
    private Boolean availableInRestaurantOnly;
}
