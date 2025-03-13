package br.com.fiap.fiappi.core.menu.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.usecase.CreateMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.DeleteMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.FindMenuByIdRestaurantUseCase;
import br.com.fiap.fiappi.core.menu.usecase.UpdateMenuUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequiredArgsConstructor
public class MenuController {

    private final CreateMenuUseCase createMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;
    private final FindMenuByIdRestaurantUseCase findMenuByIdRestaurantUseCase;
    private final UpdateMenuUseCase updateMenuUseCase;


    public void create(byte[] bytes, String restaurantId, String name, String description,
                       String price, String availableInRestaurantOnly, UUID userRequestId) {

        createMenuUseCase.create(bytes, restaurantId, name, description, price, availableInRestaurantOnly, userRequestId);
    }


    public void delete(UUID id) {
        deleteMenuUseCase.delete(id);
    }

    public List<MenuDTO> findByIdRestaurant(UUID idRestaurant) {
        return findMenuByIdRestaurantUseCase.findByIdRestaurant(idRestaurant);
    }

    public void updateMenu(MenuDTO dto, UUID userRequestId) {
        updateMenuUseCase.update(dto, userRequestId);
    }
}
