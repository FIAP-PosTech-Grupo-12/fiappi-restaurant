# FIAPPI

## Descrição

O `FIAPPI` é um microserviço desenvolvido como parte do Tech Challenge da FIAP, com foco no gerenciamento de usuários em um sistema de gestão compartilhado para restaurantes. Este serviço permite operações básicas de CRUD, validação de login e gerenciamento de senhas, utilizando Java 17, Spring Boot 3 e PostgreSQL.

Este projeto faz parte do Pós Graduação Tech Arquitetura Java da [FIAP](https://www.fiap.com.br/) e visa consolidar conhecimentos em backend com Java e arquitetura de sistemas.

## Funcionalidades

- Atualizar tipo de usuario.
- Adicionar, atualizar, remover, recuperar restaurantes.
- Adicionar, atualizar, remover, recuperar menus.
- Testes.

---

## Tecnologias Utilizadas

- Linguagem: Java 17
- Spring Boot 3 (Web, Data JPA, Security, JUnit)
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

Para utilização dos endpoints desse projeto, é necessário mandar token no Bearer Token do Postman. Para obtenção do token é necessario rodar o projeto da FASE 1 para autenticação. URL FASE 1: [https://github.com/FIAP-PosTech-Grupo-12/fiappi]

A documentação da API pode ser acessada por meio do Swagger UI, gerado automaticamente pelo Springdoc OpenAPI.

- Após inicializar a aplicação, acessar pelo seu navegador a URL: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

Essa documentação permite explorar e testar todos os endpoints diretamente no navegador.

Alternativamente, pode-se utilizar uma coleção de requisições, na qual é fornecida por um arquivo e importado pela aplicação [Postman](https://www.postman.com/downloads/), esse arquivo está localizado na pasta /src/main/resources/postman.

---

## Endpoints da API

| Método | Endpoint                  | Descrição                            |
|--------|---------------------------|--------------------------------------|
| POST   | `/v2/users`               | Atualização tipo de usuário          |
| POST   | `/v1/restaurant/{id}`     | Cria um restaurante                  |
| GET    | `/v1/restaurant/{id}`     | Buscar restaurante pelo ID           |
| GET    | `/v1/restaurant`          | Buscar todos restaurantes            |
| DELETE | `/v1/restaurant/{id}`     | Exclui restaurante                   |
| PATCH  | `/v1/restaurant/`         | Atualizar restaurante                |
| POST   | `/v1/menu/`               | Cria um menu                         |
| DELETE | `/v1/menu/{id}`           | Exclui um menu                       |
| GET    | `/v1/menu/{idRestaurant}` | Buscar todos menus de um restaurante |
| PATCH  | `/v1/menu/`               | Atualizar um menu                    |


---

## Considerações Finais

Este projeto foi desenvolvido com foco nos objetivos propostos pela Fase 2 do Tech Challenge FIAP. A documentação podendo ser utilizada através do Postman e/ou Swagger(OpenAPI) para consulta interativa. Segurança avançada e outras melhorias podem ser adicionadas futuramente conforme a evolução do projeto.

---

## Contato

- Autores: Alan Araújo, Diego Cruz, Guilherme Lima, João Victor