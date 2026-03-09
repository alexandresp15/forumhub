# FórumHub API

Projeto desenvolvido em **Java com Spring Boot** como parte do **Challenge Back-End da Alura**.

A aplicação Fórum Hub consiste em uma **API REST** para gerenciamento de tópicos de um fórum, permitindo criar, listar, atualizar e excluir tópicos.

---

# Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* PostgreSQL
* Lombok
* Maven

---

# Funcionalidades

A API possui as seguintes operações:

* Criar um novo tópico
* Listar todos os tópicos
* Buscar um tópico por ID
* Atualizar um tópico
* Excluir um tópico

Também foram implementados:

* Validação de dados
* Controle de duplicidade de tópicos
* Autenticação com Spring Security
* Persistência em banco de dados PostgreSQL

---

# Endpoints

| Método | Endpoint      | Descrição            |
| ------ | ------------- | -------------------- |
| POST   | /topicos      | Criar um tópico      |
| GET    | /topicos      | Listar tópicos       |
| GET    | /topicos/{id} | Buscar tópico por ID |
| PUT    | /topicos/{id} | Atualizar tópico     |
| DELETE | /topicos/{id} | Excluir tópico       |

---

# Exemplo de requisição

POST `/topicos`

```
{
  "titulo": "Erro no Spring Boot",
  "mensagem": "Minha aplicação não conecta ao banco",
  "autor": "Alexandre",
  "curso": "Spring Boot"
}
```

---

# Autenticação

A API utiliza **Spring Security com autenticação Basic Auth**.

Usuário de teste:

```
email: admin@email.com
senha: 123456
```

---

# Como executar o projeto

1. Clonar o repositório

```
git clone https://github.com/seu-usuario/forumhub.git
```

2. Criar banco PostgreSQL chamado

```
forumhub
```

3. Configurar o arquivo `application.properties`

```
spring.datasource.url=jdbc:postgresql://localhost:5432/forumhub
spring.datasource.username=postgres
spring.datasource.password=sua_senha

spring.jpa.hibernate.ddl-auto=update
```
