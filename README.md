# TODO list
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Anna-Caroline/api-todolist/blob/main/LICENSE) 

# Sobre o projeto
API para gerenciar tarefas (CRUD).

# Tecnologias
- Spring Boot
- Spring MVC
- Spring Data JPA
- SpringDoc OpenAPI 3
- Mysql


# Práticas adotadas
- SOLID, DRY, YAGNI, KISS
- API REST
- Consultas com Spring Data JPA
- Injeção de Dependências
- Tratamento de respostas de erro 
- Geração automática do Swagger com a OpenAPI 3
- Testes automatizados

# Como executar o projeto
- Clonar o repositório ou fazer download
- Abrir em IDE de preferência o arquivo DesafioTodoListAplication.java dentro da pasta src e rodar
<br> <br>A API poderá ser acessada em localhost:8080

# API Endpoints
Para fazer as requisições abaixo, foi utilizada a ferramenta [httpie](https://httpie.io/)

- Criar tarefa
```bash
$ http POST :8080/todos name="Todo 1" description="Desc Todo 1" priority=1

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "status": false
  }
]
```
- Listar tarefas
```bash
$ http GET :8080/todos

[
  {
    "description": "Desc Todo 1",
    "id": 1,
    "name": "Todo 1",
    "priority": 1,
    "status": false
  }
]

```
- Atualizar tarefa

```bash
$ http PUT :8080/todos/1 name="Todo 1 Up" description="Desc Todo 1 Up" priority=2

[
  {
    "description": "Desc Todo 1 Up",
    "id": 1,
    "name": "Todo 1 Up",
    "priority": 2,
    "status": false
  }
]
```
- Remover tarefa
```bash
$ http DELETE :8080/todos/1
[ ]
```

## Autora

Caroline Feitosa 

https://www.linkedin.com/in/carolinefeitosa/
