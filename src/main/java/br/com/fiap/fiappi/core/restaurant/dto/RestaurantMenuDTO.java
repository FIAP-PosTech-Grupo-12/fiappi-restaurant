package br.com.fiap.fiappi.core.restaurant.dto;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;

import java.util.List;
import java.util.UUID;

public record RestaurantMenuDTO(
        UUID id,
        String name,
        String address,
        KitchenTypeEnum kitchenType,
        String openingHours,
        UUID ownerId,
        List<MenuDTO> menus

) {
}
