package br.com.fiap.fiappi.core.menu.dto;

import java.math.BigDecimal;

public record MenuItemCreateDTO(
        String name,
        String description,
        BigDecimal price,
        Boolean restaurantOnly

) {
}
