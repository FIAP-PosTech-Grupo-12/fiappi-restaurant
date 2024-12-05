package br.com.fiap.fiappi.user.adapter.api.projection;

public interface UserDetailedProjection extends UserProjection {

    String getEmail();

    String getAddress();

}
