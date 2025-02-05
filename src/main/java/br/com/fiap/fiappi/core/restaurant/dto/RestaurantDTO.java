package br.com.fiap.fiappi.core.restaurant.dto;

import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;

import java.util.UUID;

public record RestaurantDTO(
        UUID id,
        String name,
        String address,
        KitchenTypeEnum kitchenType,
        String openingHours,
        UUID ownerId
) {
}
