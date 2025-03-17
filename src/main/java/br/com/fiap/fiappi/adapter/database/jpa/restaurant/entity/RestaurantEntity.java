package br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity;

import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.core.restaurant.enums.KitchenTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    @Enumerated(EnumType.STRING)
    private KitchenTypeEnum kitchenType;

    @Column
    private String openingHours;

    @Column
    private UUID ownerId;

    @Column
    private UUID creatorId;

    @Column
    private LocalDateTime createdAt;

    @Column
    private UUID updatedBy;

    @Column
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<MenuEntity> menus = new ArrayList<>();

    public RestaurantEntity(String name, String address, KitchenTypeEnum kitchenType, String openingHours, UUID ownerId, UUID creatorId, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
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

    public RestaurantEntity(UUID id) {
        this.id = id;
    }

    public RestaurantEntity(UUID id, String name, String address, KitchenTypeEnum kitchenType, String openingHours, UUID ownerId, UUID creatorId, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
        this.id = id;
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
