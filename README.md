# Projeto Final - Desenvolvimento de Sistemas Web II (2024.1)

Este repositório contém o projeto final da disciplina **Desenvolvimento de Sistemas Web II** da Universidade Federal do Rio Grande do Norte (UFRN), referente ao semestre 2024.1. O sistema foi desenvolvido em etapas, cada uma abordando diferentes camadas e funcionalidades de uma aplicação RESTful.

---

## Visão Geral

O sistema é uma API para gerenciamento de **alunos**, **turmas**, **professores** e **usuários**, com suporte a autenticação e autorização usando **Spring Security**. As funcionalidades foram evoluídas ao longo das unidades da disciplina, iniciando pelo CRUD básico e finalizando com a adição de segurança à aplicação.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Spring Security**
- **Maven**
- **Banco de dados H2** (pode ser adaptado para outros como PostgreSQL)
- **Lombok**

---

## Funcionalidades Implementadas

### Segurança e Autenticação

- Autenticação com Spring Security
- Controle de acesso com base em perfis (`ADMIN` e `USER`)
- Senhas criptografadas com BCrypt
- Endpoint público para login e cadastro de usuário
- Criação de usuários persistentes no banco
- Restrições de acesso por tipo de usuário:
  - **ADMIN**: pode acessar todos os métodos (GET, POST, PUT, DELETE)
  - **USER**: acesso limitado a métodos GET (consultas)

### Gerenciamento de Usuários

- Cadastro de usuários
- Login de usuários com autenticação JWT
- Associação de perfis (roles)

### Gerenciamento de Alunos

- Cadastro de alunos
- Listagem geral e por ID
- Atualização de dados
- Remoção de alunos

### Gerenciamento de Turmas

- Criação de turmas
- Listagem de todas as turmas ou por ID
- Atualização de informações
- Exclusão de turmas

### Gerenciamento de Professores

- Cadastro de professores
- Listagem de professores
- Atualização de informações
- Exclusão de professores

---

## Endpoints Principais

> Todos os endpoints de recursos exigem autenticação com exceção dos de login e cadastro de usuário.

### Autenticação

| Método | Endpoint            | Descrição                   |
|--------|---------------------|-----------------------------|
| POST   | `/auth/register`    | Cadastro de novo usuário    |
| POST   | `/auth/login`       | Login e autenticação        |

### Alunos

| Método | Endpoint       | Permissão |
|--------|----------------|-----------|
| GET    | `/alunos`      | ADMIN, USER |
| POST   | `/alunos`      | ADMIN     |
| PUT    | `/alunos/{id}` | ADMIN     |
| DELETE | `/alunos/{id}` | ADMIN     |

### Turmas

| Método | Endpoint        | Permissão |
|--------|-----------------|-----------|
| GET    | `/turmas`       | ADMIN, USER |
| POST   | `/turmas`       | ADMIN     |
| PUT    | `/turmas/{id}`  | ADMIN     |
| DELETE | `/turmas/{id}`  | ADMIN     |

### Professores

| Método | Endpoint            | Permissão |
|--------|---------------------|-----------|
| GET    | `/professores`      | ADMIN, USER |
| POST   | `/professores`      | ADMIN     |
| PUT    | `/professores/{id}` | ADMIN     |
| DELETE | `/professores/{id}` | ADMIN     |

### Usuários

| Método | Endpoint       | Permissão |
|--------|----------------|-----------|
| GET    | `/usuarios`    | ADMIN, USER |
| POST   | `/usuarios`    | ADMIN     |
| DELETE | `/usuarios`    | ADMIN     |

---

## Estrutura do Projeto

```
src
└── main
    ├── java
    │   └── br.com.projeto
    │       ├── config          # Configurações do Spring Security e filtros
    │       ├── controllers     # Controllers REST (Alunos, Turmas, Professores, Autenticação, etc.)
    │       ├── models          # Entidades JPA (Aluno, Turma, Professor, Usuario)
    │       ├── repositories    # Interfaces para acesso ao banco (CrudRepository/JpaRepository)
    │       ├── security        # Implementações de autenticação e UserDetailsService
    │       └── services        # Lógica de negócio das entidades
    └── resources
        └── application.properties  # Configurações de banco de dados e da aplicação
```



---

## Como Executar Localmente

```bash
### Clone o repositório
git clone https://github.com/leandrofelix20k/projeto-final-webII

### Acesse o diretório principal do projeto (onde está o pom.xml)
cd projeto-final-webII/Crud

### Dê permissão de execução ao Maven Wrapper, se necessário
chmod +x mvnw

### Execute o projeto
./mvnw spring-boot:run
```
