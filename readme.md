# viceri-todo-api

Projeto de API REST para um aplicativo de tarefas (TO-DO) como parte do desafio técnico de back-end Java.

## Requisitos

Para buildar e rodar a aplicação é necessário:

- [Spring Boot 3.1.0]()
- [JDK 17](https://www.oracle.com/java/technologies/downloads/)
- [Maven 3.8](https://maven.apache.org)

## Rodando a aplicação localmente

Uma das formas de executar a aplicação localmente é executando o método `main` da classe `com.viceri.TodoRestApiApplication` diretamente da sua IDE.

Você também pode utilizar o [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) da seguinte forma:

```shell
mvn spring-boot:run
```

### Deploy da aplicação

Você também pode compilar e rodar o projeto através usando o Maven
```shell
mvn package
java -jar target/easy-notes-1.0.0.jar
```

## Acessando as rotas da API

Você pode verificar e acessar as rotas da API após rodar a aplicação e acessar a documentação Swagger na URL ```"http:/server:port/swagger-ui/index.html"```
