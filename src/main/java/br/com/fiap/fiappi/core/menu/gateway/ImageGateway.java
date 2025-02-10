package br.com.fiap.fiappi.core.menu.gateway;

import java.util.Map;
import java.util.UUID;

public interface ImageGateway {
    String create(byte[] bytes, String originalFileName);

    void deleteByPath(String path);

    Map<UUID,byte[]> findBytesByPaths(Map<UUID, String> mapIdByPhotoPath);
}
