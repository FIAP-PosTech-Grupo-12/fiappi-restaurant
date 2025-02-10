package br.com.fiap.fiappi.config.security.filters;

import br.com.fiap.fiappi.adapter.database.jpa.user.repository.UserRepository;
import br.com.fiap.fiappi.config.security.providers.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.Optional;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var token = Optional.ofNullable(request.getHeader("Authorization"))
                .filter(string -> string.startsWith("Bearer "))
                .map(string -> string.substring(7))
                .orElse("");

        var decodedJWT = jwtProvider.validateAccessToken(token);

        if (decodedJWT != null && Instant.now().isBefore(decodedJWT.getExpiresAtAsInstant())) {
            userRepository.findByLogin(decodedJWT.getSubject()).ifPresent(user -> {
                var authentication = new UsernamePasswordAuthenticationToken(decodedJWT.getSubject(), null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }

        filterChain.doFilter(request, response);

    }

}
