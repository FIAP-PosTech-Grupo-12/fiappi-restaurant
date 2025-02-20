package br.com.fiap.fiappi.unitario.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.menu.usecase.FindMenuByIdRestaurantUseCaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FindMenuByIdRestaurantUseCaseTest {

    @InjectMocks
    FindMenuByIdRestaurantUseCaseImpl find;

    @Mock
    MenuGateway menuGateway;

    @Mock
    ImageGateway imageGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldFindMenuByIdRestaurant(){

        byte[] bytes = new byte[0];

        UUID uuidMenu = UUID.randomUUID();

        MenuDTO dto = new MenuDTO(uuidMenu, UUID.randomUUID(), "nameMenu", "Description", 10.00, true, bytes);

        Map<MenuDTO, String> map = new HashMap<>();
        map.put(dto, "/path");

        Map<UUID, byte[]> mapUUID = new HashMap<>();
        mapUUID.put(uuidMenu, bytes);


        when(menuGateway.findByIdRestaurant(any())).thenReturn(map);
        when(imageGateway.findBytesByPaths(any())).thenReturn(mapUUID);

        List<MenuDTO> menus = find.findByIdRestaurant(UUID.randomUUID());

        assertTrue(!menus.isEmpty());

        assertEquals(bytes, menus.get(0).getBytes());

    }

}
