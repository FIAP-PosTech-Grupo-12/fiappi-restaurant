package br.com.fiap.fiappi.core.user.controller;

import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.usecase.UserUpdateRuleUseCase;
import org.springframework.stereotype.Component;

@Component
public class UserController {

    private final UserUpdateRuleUseCase userUpdateRuleUseCase;

    public UserController(UserUpdateRuleUseCase userUpdateRuleUseCase) {
        this.userUpdateRuleUseCase = userUpdateRuleUseCase;
    }

    public void updateRoleUser(UpdateRoleUserDTO dto) {
        userUpdateRuleUseCase.updateRoleUser(dto);
    }
}
