package br.com.fiap.fiappi.login.adapter.api;

import br.com.fiap.fiappi.login.adapter.api.dto.LoginUserDto;
import br.com.fiap.fiappi.login.adapter.api.dto.TokenUserDto;
import br.com.fiap.fiappi.user.domain.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @SecurityRequirements
    @PostMapping
    public ResponseEntity<TokenUserDto> login(@RequestBody LoginUserDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

}
