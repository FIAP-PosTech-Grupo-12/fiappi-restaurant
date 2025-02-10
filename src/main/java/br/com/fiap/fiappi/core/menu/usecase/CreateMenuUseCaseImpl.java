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

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateMenuUseCaseImpl implements CreateMenuUseCase{

    private final MenuGateway menuGateway;
    private final ImageGateway imageGateway;

    @Override
    public void create(byte[] bytes, String dto, UUID userRequestId) {

        Gson gson = new Gson();

        MenuDTO menuDTO = gson.fromJson(dto, MenuDTO.class);

        String pathImage = imageGateway.create(bytes, menuDTO.getName());

        Menu menu = new Menu(new Restaurant(menuDTO.getRestaurantId()),
                menuDTO.getName(),
                menuDTO.getDescription(),
                menuDTO.getPrice(),
                menuDTO.getAvailableInRestaurantOnly(),
                pathImage,
                userRequestId,
                LocalDateTime.now(),
                userRequestId,
                LocalDateTime.now());

        menuGateway.create(menu);



    }


}
