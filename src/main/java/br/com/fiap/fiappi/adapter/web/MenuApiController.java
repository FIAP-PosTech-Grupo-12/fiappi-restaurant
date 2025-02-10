package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.menu.controller.MenuController;
import br.com.fiap.fiappi.core.restaurant.dto.RestaurantDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1/menu")
public class MenuApiController {

    private final MenuController menuController;

    public MenuApiController(MenuController menuController) {
        this.menuController = menuController;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestParam MultipartFile file,
                                       @RequestParam String dto,
                                       @AuthenticationPrincipal User userRequest) {

        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        menuController.create(bytes, dto, userRequest.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        menuController.delete(id);
        return ResponseEntity.noContent().build();
    }


}
