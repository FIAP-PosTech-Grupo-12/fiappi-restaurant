package br.com.fiap.fiappi.adapter.database.jpa.menu.entity;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestaurantEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="menu_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ToString.Exclude
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Double price;

    @Column
    private Boolean availableInRestaurantOnly;

    @Column
    private String photoPath;

    @Column
    private UUID creatorId;

    @Column
    private LocalDateTime createdAt;

    @Column
    private UUID updatedBy;

    @Column
    private LocalDateTime updatedAt;

    public MenuEntity(RestaurantEntity restaurant, String name, String description, Double price, Boolean availableInRestaurantOnly, String photoPath, UUID creatorId, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
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
