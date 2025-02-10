package br.com.fiap.fiappi.adapter.database.jpa.menu.repository;

import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestauranteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, UUID> {
}
