package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.domain.Menu;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateMenuUseCaseImpl implements UpdateMenuUseCase{

    private final MenuGateway menuGateway;
    private final ImageGateway imageGateway;


    @Override
    public void update(MenuDTO menuDTO, UUID userRequestId) {

        String pathImage = menuGateway.findPathByID(menuDTO.getMenuId());

        Menu menu = new Menu(menuDTO.getMenuId(),
                new Restaurant(menuDTO.getRestaurantId()),
                menuDTO.getName(),
                menuDTO.getDescription(),
                menuDTO.getPrice(),
                menuDTO.getAvailableInRestaurantOnly(),
                pathImage,
                userRequestId,
                LocalDateTime.now(),
                userRequestId,
                LocalDateTime.now());

        menuGateway.update(menu);
    }
}
