# FIAPPI

## Descrição

O `FIAPPI` é um microserviço desenvolvido como parte do Tech Challenge da FIAP, com foco no gerenciamento de usuários em um sistema de gestão compartilhado para restaurantes. Este serviço permite operações básicas de CRUD, validação de login e gerenciamento de senhas, utilizando Java 17, Spring Boot 3 e PostgreSQL.

Este projeto faz parte do Pós Graduação Tech Arquitetura Java da [FIAP](https://www.fiap.com.br/) e visa consolidar conhecimentos em backend com Java e arquitetura de sistemas.

## Funcionalidades

- Atualizar tipo de usuario.
- Adicionar, atualizar, remover, recuperar restaurantes.
- Adicionar, atualizar, remover, recuperar menus.

---

## Tecnologias Utilizadas

- Linguagem: Java 17
- Spring Boot 3 (Web, Data JPA, Security)
- PostgreSQL
- Lombok
- Docker e Docker Compose
- Documentação Postman e Swagger/OpenAPI

---

## Instalação e Execução

### Pré-requisitos

- Docker e Docker Compose instalados
- JDK 17 ou superior

### Passos

1. Clone o repositório:

   ```bash
   git clone https://github.com/FIAP-PosTech-Grupo-12/fiappi-restaurant.git
   cd fiappi-restaurant
   ```

2. Suba o ambiente com Docker Compose:

   ```bash
   docker-compose up --build
   ```

---

## Utilização

A aplicação possui um usuário Master inicial, pois é necessário o login deste usuário para efetuar as chamadas aos outros endpoints, respeitando a segurança da API.

**As credenciais estão definidas como usuário "master" e senha "fiap"**

A documentação da API pode ser acessada por meio do Swagger UI, gerado automaticamente pelo Springdoc OpenAPI.

- Após inicializar a aplicação, acessar pelo seu navegador a URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Essa documentação permite explorar e testar todos os endpoints diretamente no navegador.

Alternativamente, pode-se utilizar uma coleção de requisições, na qual é fornecida por um arquivo e importado pela aplicação [Postman](https://www.postman.com/downloads/), esse arquivo está localizado na pasta /src/main/resources/postman.

---

## Endpoints da API

| Método | Endpoint                  | Descrição                                                                         |
|--------|---------------------------|-----------------------------------------------------------------------------------|
| POST   | `/v1/login`               | Validar login e senha                                                             |
| POST   | `/v1/users`               | Criação de usuário (Apenas administrador)                                         |
| GET    | `/v1/users/{id}`          | Buscar todos os usuários (Administrador retorna todos, usuário retorna ele mesmo) |
| GET    | `/v1/users/{id}`          | Buscar usuário pelo ID (Administrador ou próprio usuário)                         |
| PATCH  | `/v1/users/{id}`          | Atualizar dados do usuário (Administrador ou próprio usuário)                     |
| PATCH  | `/v1/users/{id}/password` | Atualizar senha do usuário (Administrador ou próprio usuário)                     |
| DELETE | `/v1/users/{id}`          | Excluir usuário (Administrador ou próprio usuário)                                |

---

## Considerações Finais

Este projeto foi desenvolvido com foco nos objetivos propostos pela Fase 1 do Tech Challenge FIAP. A documentação podendo ser utilizada através do Postman e/ou Swagger(OpenAPI) para consulta interativa. Testes unitários, segurança avançada e outras melhorias podem ser adicionadas futuramente conforme a evolução do projeto.

---

## Contato

- Autores: Alan Araújo, Diego Cruz, Guilherme Lima, João Victor