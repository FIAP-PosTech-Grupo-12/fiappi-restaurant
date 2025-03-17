package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeleteMenuUseCaseImpl implements DeleteMenuUseCase{

    private final MenuGateway menuGateway;
    private final ImageGateway imageGateway;


    @Override
    public void delete(UUID id) {

        String path = menuGateway.findPathByID(id);

        imageGateway.deleteByPath(path);

        menuGateway.deleteById(id);

    }
}
