package br.com.fiap.fiappi.core.menu.controller;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.usecase.CreateMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.DeleteMenuUseCase;
import br.com.fiap.fiappi.core.menu.usecase.FindMenuByIdRestaurantUseCase;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MenuController {

    private final CreateMenuUseCase createMenuUseCase;
    private final DeleteMenuUseCase deleteMenuUseCase;
    private final FindMenuByIdRestaurantUseCase findMenuByIdRestaurantUseCase;


    public void create(byte[] bytes, String dto, UUID userRequestId) {

        createMenuUseCase.create(bytes, dto, userRequestId);
    }


    public void delete(UUID id) {
        deleteMenuUseCase.delete(id);
    }

    public Set<MenuDTO> findByIdRestaurant(UUID idRestaurant) {
        return findMenuByIdRestaurantUseCase.findByIdRestaurant(idRestaurant);
    }
}
