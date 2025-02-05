package br.com.fiap.fiappi.core.restaurant.domain;

import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class Restaurant {
    private UUID id;
    private String name;
    private String address;
    private KitchenTypeEnum kitchenType;
    private String openingHours;
    private UUID ownerId;
    private UUID creatorId;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public Restaurant(String name, String address, KitchenTypeEnum kitchenType, String openingHours, UUID ownerId, UUID creatorId, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
        this.name = name;
        this.address = address;
        this.kitchenType = kitchenType;
        this.openingHours = openingHours;
        this.ownerId = ownerId;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }


}
