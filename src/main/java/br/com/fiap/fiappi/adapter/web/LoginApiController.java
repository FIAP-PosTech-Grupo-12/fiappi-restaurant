package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.core.user.controller.UserController;
import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/v2/login")
public class LoginApiController {

    private final UserController userController;

    @SecurityRequirements
    @PostMapping
    public ResponseEntity<TokenUserDto> login(@RequestBody LoginUserDto dto) {
        return ResponseEntity.ok(userController.login(dto));
    }

}
