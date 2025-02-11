package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DeleteMenuUseCaseTest {

    @InjectMocks
    DeleteMenuUseCaseImpl deleteMenu;

    @Mock
    MenuGateway menuGateway;

    @Mock
    ImageGateway imageGateway;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldDeleteMenu(){

        when(menuGateway.findPathByID(any())).thenReturn("/path");

        deleteMenu.delete(UUID.randomUUID());

        verify(imageGateway, times(1)).deleteByPath(any());
        verify(menuGateway, times(1)).deleteById(any());

    }

}
