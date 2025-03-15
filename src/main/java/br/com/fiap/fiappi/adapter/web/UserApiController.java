package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import br.com.fiap.fiappi.core.user.projection.UserProjection;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/v2/users")
public class UserApiController {

    private final UserController userController;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        userController.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserProjection>> listUsers(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(userController.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedProjection> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userController.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDto dto) {
        userController.updateUser(id, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody ChangeUserPasswordDto dto) {
        userController.changeUserPassword(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userController.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    public ResponseEntity<Void> updateRule(@RequestBody UpdateRoleUserDTO dto) {
        userController.updateRoleUser(dto);
        return ResponseEntity.ok().build();
    }

}
