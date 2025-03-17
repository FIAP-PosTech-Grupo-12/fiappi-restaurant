package br.com.fiap.fiappi.adapter.database.jpa.restaurant.repository;

import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<RestaurantEntity, UUID> {
}
