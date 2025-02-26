package br.com.fiap.fiappi.unitario.core.menu.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.menu.usecase.UpdateMenuUseCaseImpl;

public class UpdateMenuUseCaseTest {

    @InjectMocks
    UpdateMenuUseCaseImpl updateMenu;

    @Mock
    MenuGateway menuGateway;

    @Mock
    ImageGateway imageGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldUpdateMenu(){

        byte[] bytes = new byte[0];

        UUID uuidMenu = UUID.randomUUID();

        MenuDTO dto = new MenuDTO(uuidMenu, UUID.randomUUID(), "nameMenu", "Description", 10.00, true, bytes);

        when(menuGateway.findPathByID(any())).thenReturn("/path");

        updateMenu.update(dto, UUID.randomUUID());

        verify(menuGateway, times(1)).update(any());

    }

}
