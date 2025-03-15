package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.domain.Menu;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.restaurant.domain.Restaurant;
import br.com.fiap.fiappi.core.restaurant.gateway.RestaurantGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreateMenuUseCaseImpl implements CreateMenuUseCase{

    private final MenuGateway menuGateway;
    private final ImageGateway imageGateway;
    private final RestaurantGateway restaurantGateway;

    @Override
    public void create(byte[] bytes, String restaurantId, String name, String description,
                       String price, String availableInRestaurantOnly, UUID userRequestId) {

        UUID uuidRestaurant = UUID.fromString(restaurantId);

        restaurantGateway.findBy(uuidRestaurant);

        String pathImage = imageGateway.create(bytes, name);

        Menu menu = new Menu(new Restaurant(uuidRestaurant),
                name,
                description,
                Double.valueOf(price),
                Boolean.valueOf(availableInRestaurantOnly),
                pathImage,
                userRequestId,
                LocalDateTime.now(),
                userRequestId,
                LocalDateTime.now());

        menuGateway.create(menu);



    }


}
