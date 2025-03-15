package br.com.fiap.fiappi.adapter.database.jpa.menu;

import br.com.fiap.fiappi.core.menu.exception.ImageConverterException;
import br.com.fiap.fiappi.core.menu.gateway.ImageGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageSpringGateway implements ImageGateway {


    @Override
    public String create(byte[] bytes, String fileName) {

        String nameWithUUID = "/static/images/" + fileName + UUID.randomUUID();

        Path path = Paths.get("src/main/resources" + nameWithUUID);

        try {
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            throw new ImageConverterException("Error to create directory");
        }

        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new ImageConverterException("Error to create image");
        }

        return nameWithUUID;
    }

    @Override
    @Transactional
    public void deleteByPath(String path) {

        Path pathWithName = Paths.get("src/main/resources" + path);

        if (Files.exists(pathWithName)) {
            try {
                Files.delete(pathWithName);
            } catch (IOException e) {
                throw new ImageConverterException("Error to delete image");
            }
        }
    }

    @Override
    public Map<UUID, byte[]> findBytesByPaths(Map<UUID, String> mapIdByPhotoPath) {

        Map<UUID, byte[]> mapIdByBytes = new HashMap();

        mapIdByPhotoPath.forEach((id, photoPath) -> {
            Path pathWithName = Paths.get("src/main/resources" + photoPath);

            byte[] bytes;
            try {
                bytes = Files.readAllBytes(pathWithName);
            } catch (IOException e) {
                throw new ImageConverterException("Error to read image");
            }

            mapIdByBytes.put(id, bytes);

        });


        return mapIdByBytes;
    }
}
