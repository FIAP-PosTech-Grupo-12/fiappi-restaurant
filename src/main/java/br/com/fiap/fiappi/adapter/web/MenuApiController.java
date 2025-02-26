package br.com.fiap.fiappi.adapter.web;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.menu.controller.MenuController;
import br.com.fiap.fiappi.core.menu.dto.MenuDTO;
import br.com.fiap.fiappi.core.menu.exception.MenuConverterException;
import lombok.extern.slf4j.Slf4j;

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
            throw new MenuConverterException("Error to get bytes");
        }

        menuController.create(bytes, dto, userRequest.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        menuController.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idRestaurant}")
    public ResponseEntity<List<MenuDTO>> findByIdRestaurant(@PathVariable UUID idRestaurant) {
        return ResponseEntity.ok(menuController.findByIdRestaurant(idRestaurant));
    }

    @PatchMapping()
    public ResponseEntity<Void> updateMenu(@RequestBody MenuDTO dto,
                                           @AuthenticationPrincipal User userRequest) {
        menuController.updateMenu(dto, userRequest.getId());
        return ResponseEntity.ok().build();
    }

}
