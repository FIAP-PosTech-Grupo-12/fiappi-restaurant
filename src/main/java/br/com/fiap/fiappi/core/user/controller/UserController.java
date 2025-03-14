package br.com.fiap.fiappi.core.user.controller;

import br.com.fiap.fiappi.core.user.dto.CreateUserDto;
import br.com.fiap.fiappi.core.user.dto.UpdateRoleUserDTO;
import br.com.fiap.fiappi.core.user.usecase.*;
import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.UpdateUserDto;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import br.com.fiap.fiappi.core.user.projection.UserProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;
    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindByIdUserUseCase findByIdUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final ChangeUserPasswordUseCase changeUserPasswordUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final UserUpdateRuleUseCase userUpdateRuleUseCase;

    public void createUser(CreateUserDto dto) {
        createUserUseCase.create(dto);
    }

    public TokenUserDto login(LoginUserDto dto) {
        return loginUseCase.login(dto);
    }

    public List<UserProjection> findAll(Integer page, Integer size) {
        return findAllUsersUseCase.findAll(page, size);
    }

    public UserDetailedProjection findById(UUID id) {
        return findByIdUserUseCase.findById(id);
    }

    public void updateUser(UUID id, UpdateUserDto dto) {
        updateUserUseCase.update(dto, id);
    }

    public void changeUserPassword(UUID id, ChangeUserPasswordDto dto) {
        changeUserPasswordUseCase.changePassword(id, dto);
    }

    public void deleteUser(UUID id) {
        deleteUserUseCase.delete(id);
    }

    public void updateRoleUser(UpdateRoleUserDTO dto) {
        userUpdateRuleUseCase.updateRoleUser(dto);
    }
}
