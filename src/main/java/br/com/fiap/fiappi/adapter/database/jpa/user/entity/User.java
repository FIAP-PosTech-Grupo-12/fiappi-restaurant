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
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public User(String name, String email, String login, String password, LocalDateTime updatedDate, String address, RoleName role) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
        this.updatedDate = updatedDate;
        this.address = address;
        this.role = role;
    }

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

