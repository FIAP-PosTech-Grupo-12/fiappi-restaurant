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

import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import br.com.fiap.fiappi.core.menu.usecase.DeleteMenuUseCaseImpl;

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
