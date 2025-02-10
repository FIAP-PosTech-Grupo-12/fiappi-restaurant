package br.com.fiap.fiappi.core.menu.gateway;

public interface ImageGateway {
    String create(byte[] bytes, String originalFileName);

    void deleteByPath(String path);
}
