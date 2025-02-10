package br.com.fiap.fiappi.adapter.database.jpa.user.entity;


import br.com.fiap.fiappi.core.user.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private UUID id;

    @Setter
    private String name;

    @Setter
    private String email;

    private String login;

    @Setter
    private String password;

    @Setter
    private LocalDateTime updatedDate;

    @Setter
    private String address;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    public boolean isLoggedUser(String login) {
        return this.login.equalsIgnoreCase(login);
    }

}

