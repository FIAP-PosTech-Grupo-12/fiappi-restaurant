package br.com.fiap.fiappi.config.security.service;

import br.com.fiap.fiappi.user.domain.enums.RoleName;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public boolean isAuthenticatedUserAdmin() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(ga -> ga.getAuthority().equals(RoleName.ROLE_ADMINISTRATOR.name()));
    }

    public boolean isAuthenticatedUser() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(ga -> ga.getAuthority().equals(RoleName.ROLE_CUSTOMER.name()));
    }

    public void checkIfAuthenticated() {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .noneMatch(ga -> RoleName.ROLE_ADMINISTRATOR.name().equalsIgnoreCase(ga.getAuthority()) ||
                        RoleName.ROLE_CUSTOMER.name().equalsIgnoreCase(ga.getAuthority())))
            throw new BadCredentialsException("User not authenticated");
    }

    public String getAuthenticatedUserName() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
