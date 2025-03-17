package br.com.fiap.fiappi.core.user.projection;

public interface UserDetailedProjection extends UserProjection {

    String getEmail();

    String getAddress();

}
