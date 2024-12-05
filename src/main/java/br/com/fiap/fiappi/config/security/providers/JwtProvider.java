package br.com.fiap.fiappi.config.security.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class JwtProvider {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.server.url}")
    private String issuerUrl;

    private static final String REFRESH_TYPE = "refreshToken";
    private static final String ACCESS_TYPE = "accessToken";

    public String generateRefreshToken(String subject) {
        final var expiration = Instant.now().plusSeconds(360 * 4);

        try {
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(subject)
                    .withClaim("type", REFRESH_TYPE)
                    .withExpiresAt(expiration)
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating refresh token", exception);
        }
    }

    public String generateAccessToken(String subject) {
        final Instant expiration = Instant.now().plusSeconds(360);

        try {
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(subject)
                    .withClaim("type", ACCESS_TYPE)
                    .withExpiresAt(expiration)
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating access token", exception);
        }
    }

    public DecodedJWT validateAccessToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer(issuerUrl)
                    .withClaim("type", ACCESS_TYPE)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String validateRefreshToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer(issuerUrl)
                    .withClaim("type", REFRESH_TYPE)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }

}
