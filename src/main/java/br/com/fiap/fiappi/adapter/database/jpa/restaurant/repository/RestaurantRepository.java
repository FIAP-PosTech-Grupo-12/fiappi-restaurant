package br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RestaurantRepository extends JpaRepository<RestauranteEntity, UUID> {
}
