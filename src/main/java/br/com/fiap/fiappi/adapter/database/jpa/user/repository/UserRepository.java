package br.com.fiap.fiappi.adapter.database.jpa.user.repository;

import br.com.fiap.fiappi.adapter.database.jpa.user.entity.User;
import br.com.fiap.fiappi.core.user.enums.RoleName;
import br.com.fiap.fiappi.core.user.projection.UserDetailedProjection;
import br.com.fiap.fiappi.core.user.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByLogin(String login);

    @Query("SELECT user FROM User user WHERE user.id = :id")
    Optional<UserDetailedProjection> findByUserId(UUID id);

    @SuppressWarnings("all")
    @Query(value = "SELECT * FROM users WHERE :login IS NULL OR users.login = :login ORDER BY name LIMIT :size OFFSET (SELECT :page * :size)", nativeQuery = true)
    List<UserProjection> findAllOrLoginNameUser(@Param("page") Integer page, @Param("size") Integer size, @Param("login") String login);

    Optional<User> findByEmailOrLogin(String email, String login);

    @Transactional
    @Modifying
    @Query("update User u set u.role = ?1 where u.id = ?2")
    int updateRoleById(RoleName role, UUID id);
}
