# Literalura

**Literalura** é um projeto em Java que cria uma API para interagir com o [Gutendex](https://gutendex.com), uma API pública de livros, e um banco de dados local gerenciado pelo **pgAdmin**.

Com este projeto, você pode buscar livros pelo título, autor ou idioma, exibi-los no terminal e salvar os dados selecionados no seu banco de dados PostgreSQL local.

---

## Funcionalidades principais

- Consulta livros disponíveis na API do Gutendex.
- Busca por título, autor ou combinação de ambos.
- Visualização e salvamento opcional dos livros no banco local.
- Consulta estatísticas sobre livros por idioma.
- Listagem de autores vivos em um ano específico.
- Persistência de dados usando JPA e Hibernate com PostgreSQL.
- Interface simples via terminal para interação do usuário.

---

## Tecnologias utilizadas

- Java 17+
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- pgAdmin (para gerenciar o banco de dados local)
- API Gutendex ([https://gutendex.com](https://gutendex.com))

---

## Como usar

1. Clone o repositório.
2. Configure o banco PostgreSQL localmente e certifique-se que o pgAdmin está acessível.
3. Ajuste as configurações de conexão no `application.properties` para seu banco.
4. Execute a aplicação Java.
5. Use o menu interativo para buscar livros e salvar no banco.

---

## Referência do desafio

Este projeto foi desenvolvido a partir do desafio disponível em:  
[https://trello.com/b/eLc9Sj1A/literalura-challenge-java](https://trello.com/b/eLc9Sj1A/literalura-challenge-java)

---
