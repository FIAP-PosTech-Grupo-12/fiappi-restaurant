package br.com.fiap.fiappi.core.user.domain;

import br.com.fiap.fiappi.core.user.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private String email;
    private String login;
    private String password;
    private LocalDateTime updatedDate;
    private String address;
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

    public User(UUID id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    public User(UUID id) {
        this.id = id;
    }

}