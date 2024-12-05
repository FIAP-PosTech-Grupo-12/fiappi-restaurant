package br.com.fiap.fiappi.user.adapter.api;

import br.com.fiap.fiappi.user.adapter.api.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.user.adapter.api.dto.CreateUserDto;
import br.com.fiap.fiappi.user.adapter.api.dto.UpdateUserDto;
import br.com.fiap.fiappi.user.adapter.api.projection.UserDetailedProjection;
import br.com.fiap.fiappi.user.adapter.api.projection.UserProjection;
import br.com.fiap.fiappi.user.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto dto) {
        userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<UserProjection>> listUsers(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailedProjection> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable UUID id, @RequestBody UpdateUserDto dto) {
        userService.updateUser(id, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody ChangeUserPasswordDto dto) {
        userService.changeUserPassword(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
