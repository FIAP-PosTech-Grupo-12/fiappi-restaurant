package br.com.fiap.fiappi.adapter.web;

import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.controller.UserController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/v2/users")
public class UserApiController {

    private final UserController userController;

    @PatchMapping()
    public ResponseEntity<Void> updateRule(@RequestBody UpdateRoleUserDTO dto) {
        userController.updateRoleUser(dto);
        return ResponseEntity.ok().build();
    }



}
