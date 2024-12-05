package br.com.fiap.fiappi.user.domain.service;

import br.com.fiap.fiappi.config.security.providers.JwtProvider;
import br.com.fiap.fiappi.config.security.service.AuthenticationService;
import br.com.fiap.fiappi.login.adapter.api.dto.LoginUserDto;
import br.com.fiap.fiappi.login.adapter.api.dto.TokenUserDto;
import br.com.fiap.fiappi.user.adapter.api.dto.ChangeUserPasswordDto;
import br.com.fiap.fiappi.user.adapter.api.dto.CreateUserDto;
import br.com.fiap.fiappi.user.adapter.api.dto.UpdateUserDto;
import br.com.fiap.fiappi.user.adapter.api.projection.UserDetailedProjection;
import br.com.fiap.fiappi.user.adapter.api.projection.UserProjection;
import br.com.fiap.fiappi.user.domain.model.User;
import br.com.fiap.fiappi.user.domain.model.UserRepository;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class UserService {

    private final JwtProvider jwtProvider;

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationService authenticationService;

    public void createUser(CreateUserDto dto) {

        //authenticationService.checkIfAuthenticated();

        repository.findByEmailOrLogin(dto.email(), dto.login()).ifPresentOrElse(ignored -> {
            throw new IllegalArgumentException("Email or login already in use");
        }, () -> {
            var user = br.com.fiap.fiappi.user.domain.model.User.builder()
                    .id(UUID.randomUUID())
                    .name(dto.name())
                    .email(dto.email())
                    .login(dto.login())
                    .password(passwordEncoder.encode(dto.password()))
                    .updatedDate(LocalDateTime.now())
                    .role(dto.role())
                    .address(dto.address())
                    .build();

            repository.saveAndFlush(user);
        });

    }

    public TokenUserDto login(LoginUserDto dto) {

        var userOptional = repository.findByLogin(dto.login());

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

    public List<UserProjection> findAll(Integer page, Integer size) {

        //authenticationService.checkIfAuthenticated();

        if (authenticationService.isAuthenticatedUserAdmin())
            return repository.findAllOrLoginNameUser(page, size, null);
        else if (authenticationService.isAuthenticatedUser())
            return repository.findAllOrLoginNameUser(page, size, authenticationService.getAuthenticatedUserName());
        else
            throw new AuthorizationDeniedException("Is not an administrator", new AuthorizationDecision(false));

    }

    public UserDetailedProjection findById(UUID id) {

        //authenticationService.checkIfAuthenticated();

        var user = getAdminOrLoggedUser(id);

        return repository.findByUserId(user.getId()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

    public void updateUser(UUID id, UpdateUserDto dto) {

        //authenticationService.checkIfAuthenticated();

        var user = getAdminOrLoggedUser(id);

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAddress(dto.address());
        user.setUpdatedDate(LocalDateTime.now());

        repository.saveAndFlush(user);

    }

    public void changeUserPassword(UUID id, ChangeUserPasswordDto dto) {

        //authenticationService.checkIfAuthenticated();

        var user = getAdminOrLoggedUser(id);

        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setUpdatedDate(LocalDateTime.now());

        repository.saveAndFlush(user);

    }

    public void deleteUser(UUID id) {

        //authenticationService.checkIfAuthenticated();

        var user = getAdminOrLoggedUser(id);

        if (!user.getLogin().equalsIgnoreCase("master"))
            repository.delete(user);
        else
            throw new IllegalArgumentException("Master user cannot be deleted");

    }

    private User getAdminOrLoggedUser(UUID id) {

        var user = repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (authenticationService.isAuthenticatedUserAdmin() || user.isLoggedUser(authenticationService.getAuthenticatedUserName()))
            return user;
        else
            throw new BadCredentialsException("Is not an administrator or a logged user");

    }

}
