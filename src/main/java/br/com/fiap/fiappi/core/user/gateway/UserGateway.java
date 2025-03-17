package br.com.fiap.fiappi.core.user.gateway;

import br.com.fiap.fiappi.core.user.domain.User;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import br.com.fiap.fiappi.core.user.projection.UserProjection;

import java.util.List;
import java.util.UUID;

public interface UserGateway {
    void create(User user);
    TokenUserDto login(LoginUserDto dto);
    List<UserProjection> findAll(Integer page, Integer size);
    UserDetailedProjection findBy(UUID id);
    void update(User user);
    void changePassword(UUID id, ChangeUserPasswordDto dto);
    void delete(UUID id);
    void updateRule(UUID id, RoleName roleName);
}
