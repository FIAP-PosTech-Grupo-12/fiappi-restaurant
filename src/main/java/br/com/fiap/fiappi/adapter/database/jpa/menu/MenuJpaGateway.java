package br.com.fiap.fiappi.adapter.database.jpa.menu;

import br.com.fiap.fiappi.adapter.database.jpa.menu.entity.MenuEntity;
import br.com.fiap.fiappi.adapter.database.jpa.menu.repository.MenuRepository;
import br.com.fiap.fiappi.adapter.database.jpa.restaurant.entity.RestauranteEntity;
import br.com.fiap.fiappi.core.menu.domain.Menu;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.exception.MenuNotFoundException;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.exception.RestaurantNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuJpaGateway implements MenuGateway {

    private final MenuRepository menuRepository;

    @Override
    @Transactional
    public void create(Menu menu) {

        MenuEntity menuEntity = new MenuEntity(new RestauranteEntity(menu.getRestaurant().getId()),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getAvailableInRestaurantOnly(),
                menu.getPhotoPath(),
                menu.getCreatorId(),
                menu.getCreatedAt(),
                menu.getUpdatedBy(),
                menu.getUpdatedAt());

        menuRepository.save(menuEntity);

    }

    @Override
    public String findPathByID(UUID id) {
        MenuEntity menuEntity = menuRepository.findById(id).orElseThrow(() -> new MenuNotFoundException("Menu not found"));

        return menuEntity.getPhotoPath();
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {

        menuRepository.deleteById(id);

    }

    @Override
    public Map<MenuDTO, String> findByIdRestaurant(UUID idRestaurant) {

        Map<MenuEntity, String> mapMenusEntityByPhotoPath = menuRepository.findByRestaurant_Id(idRestaurant).
                stream().collect(Collectors.toMap(Function.identity(), MenuEntity::getPhotoPath));

        Map<MenuDTO, String> mapMenusDTOByPhotoPath = new HashMap<>();

        mapMenusEntityByPhotoPath.forEach((menuEntity, photoPath) -> {
            MenuDTO menuDTO = new MenuDTO(idRestaurant,
                    menuEntity.getName(),
                    menuEntity.getDescription(),
                    menuEntity.getPrice(),
                    menuEntity.getAvailableInRestaurantOnly());
            mapMenusDTOByPhotoPath.put(menuDTO, photoPath);
        });

        return mapMenusDTOByPhotoPath;
    }

    @Override
    public void update(Menu menu) {
        MenuEntity menuEntity = new MenuEntity(
                menu.getId(),
                new RestauranteEntity(menu.getRestaurant().getId()),
                menu.getName(),
                menu.getDescription(),
                menu.getPrice(),
                menu.getAvailableInRestaurantOnly(),
                menu.getPhotoPath(),
                menu.getCreatorId(),
                menu.getCreatedAt(),
                menu.getUpdatedBy(),
                menu.getUpdatedAt());

        menuRepository.save(menuEntity);
    }
}
