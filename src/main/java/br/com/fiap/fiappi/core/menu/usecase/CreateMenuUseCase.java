package br.com.fiap.fiappi.core.menu.usecase;

import java.util.UUID;

public interface CreateMenuUseCase {
    void create(byte[] bytes, String restaurantId, String name, String description,
                String price, String availableInRestaurantOnly, UUID userRequestId);
}
