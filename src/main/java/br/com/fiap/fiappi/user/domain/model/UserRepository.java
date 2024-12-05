package br.com.fiap.fiappi.user.domain.model;

import br.com.fiap.fiappi.user.adapter.api.projection.UserDetailedProjection;
import br.com.fiap.fiappi.user.adapter.api.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);

    @Query("SELECT user FROM User user WHERE user.id = :id")
    Optional<UserDetailedProjection> findByUserId(UUID id);

    @SuppressWarnings("all")
    @Query(value = "SELECT * FROM users WHERE :login IS NULL OR users.login = :login ORDER BY name LIMIT :size OFFSET (SELECT (:page - 1) * :size)", nativeQuery = true)
    List<UserProjection> findAllOrLoginNameUser(@Param("page") Integer page, @Param("size") Integer size, @Param("login") String login);

    Optional<User> findByEmailOrLogin(String email, String login);

}
