package br.com.fiap.fiappi.adapter.database.jpa.user;

import br.com.fiap.fiappi.config.security.providers.JwtProvider;
import br.com.fiap.fiappi.config.security.service.AuthenticationService;
import br.com.fiap.fiappi.core.user.domain.User;
import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.core.user.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.core.user.dto.LoginUserDto;
import br.com.fiap.fiappi.core.user.dto.TokenUserDto;
import br.com.fiap.fiappi.core.user.gateway.UserGateway;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import br.com.fiap.fiappi.core.user.projection.UserProjection;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public void create(User user) {

        var userEntity = new br.com.fiap.fiappi.adapter.database.jpa.user.entity.User(user.getName(),
                user.getEmail(),
                user.getLogin(),
                passwordEncoder.encode(user.getPassword()),
                user.getUpdatedDate(),
                user.getAddress(),
                user.getRole());

        userRepository.save(userEntity);
    }

    @Override
    public TokenUserDto login(LoginUserDto dto) {

        var userOptional = userRepository.findByLogin(dto.login());

        if (userOptional.isPresent()) {

            var user = userOptional.get();
            var isValidPassword = passwordEncoder.matches(dto.password(), user.getPassword());

            if (!isValidPassword) {
                throw new BadCredentialsException("Invalid password");
            }

            return new TokenUserDto(
                    jwtProvider.generateAccessToken(user.getUsername()),
                    jwtProvider.generateRefreshToken(user.getUsername())
            );

        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public List<UserProjection> findAll(Integer page, Integer size) {

        if (authenticationService.isAuthenticatedUserAdmin())
            return userRepository.findAllOrLoginNameUser(page, size, null);
        else if (authenticationService.isAuthenticatedUser())
            return userRepository.findAllOrLoginNameUser(page, size, authenticationService.getAuthenticatedUserName());
        else
            throw new AuthorizationDeniedException("Is not an administrator", new AuthorizationDecision(false));
    }

    @Override
    public UserDetailedProjection findBy(UUID id) {

        var user = getAdminOrLoggedUser(id);

        return userRepository.findByUserId(user.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public void update(User user) {

        var userEntity = getAdminOrLoggedUser(user.getId());

        userEntity.setName(user.getName());
        userEntity.setEmail(user.getEmail());
        userEntity.setAddress(user.getAddress());
        userEntity.setUpdatedDate(LocalDateTime.now());

        userRepository.saveAndFlush(userEntity);
    }

    @Override
    public void changePassword(UUID id, ChangeUserPasswordDto dto) {

        var user = getAdminOrLoggedUser(id);

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUpdatedDate(LocalDateTime.now());

        userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(UUID id) {
        var user = getAdminOrLoggedUser(id);

        if (!user.getLogin().equalsIgnoreCase("master"))
            userRepository.delete(user);
        else
            throw new IllegalArgumentException("Master user cannot be deleted");
    }

    @Override
    public void updateRule(UUID id, RoleName roleName) {
        userRepository.updateRoleById(roleName, id);
    }

    private br.com.fiap.fiappi.adapter.database.jpa.user.entity.User getAdminOrLoggedUser(UUID id) {

        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (authenticationService.isAuthenticatedUserAdmin() || user.isLoggedUser(authenticationService.getAuthenticatedUserName()))
            return user;
        else
            throw new BadCredentialsException("Is not an administrator or a logged user");

    }
}
