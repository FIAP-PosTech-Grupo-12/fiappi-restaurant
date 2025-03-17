package br.com.fiap.fiappi.core.menu.usecase;

import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import br.com.fiap.fiappi.core.menu.gateway.MenuGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class FindMenuByIdRestaurantUseCaseImpl implements FindMenuByIdRestaurantUseCase{

    private final MenuGateway menuGateway;
    private final ImageGateway imageGateway;


    @Override
    public List<MenuDTO> findByIdRestaurant(UUID idRestaurant) {

        Map<MenuDTO, String> mapMenusDTOByPhotoPath = menuGateway.findByIdRestaurant(idRestaurant);

        Map<UUID, String> mapIdByPath = mapMenusDTOByPhotoPath.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey().getMenuId(), Map.Entry::getValue));

        Map<UUID, byte[]> mapMenuIdByBytesPhoto = imageGateway.findBytesByPaths(mapIdByPath);

        mapMenusDTOByPhotoPath.forEach((menuDTO, photoPath) -> {
            menuDTO.setBytes(mapMenuIdByBytesPhoto.get(menuDTO.getMenuId()));

        });

        return mapMenusDTOByPhotoPath.keySet().stream().toList();
    }
}
