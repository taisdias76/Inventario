# Inventario

Este é um projeto de sistema de Inventário desenvolvido em Java utilizando o framework Spring Boot. O objetivo é facilitar o gerenciamento de itens, categorias e movimentações de estoque, permitindo controle eficiente.

## Funcionalidades

- Cadastro, edição e remoção de itens do inventário
- Categorização dos itens
- Registro de entradas e saídas (movimentações de estoque)
- Relatórios de estoque atual
- API RESTful para integração com outros sistemas

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot** (Web, Data JPA)
- **Banco de Dados**: H2 (padrão, fácil substituição por MySQL, PostgreSQL, etc)
- **Maven** para gerenciamento de dependências
- **Lombok** para redução de boilerplate

## Como Executar

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/taisdias76/Inventario.git
   cd Inventario
   ```

2. **Configure o banco de dados** (opcional):  
   Por padrão, o projeto utiliza o banco de dados H2 em memória. Para usar outro banco, edite o arquivo `application.properties`.

3. **Compile o projeto:**
   ```bash
   mvn clean install
   ```

4. **Execute a aplicação:**
   ```bash
   mvn spring-boot:run
   ```

5. **Acesse a aplicação:**
   - API: `http://localhost:8080`
   - (Opcional) Swagger: `http://localhost:8080/swagger-ui.html`

## Estrutura do Projeto

```
Inventario/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   │   └── ... (código fonte)
 │   │   └── resources/
 │   │       └── application.properties
 │   └── test/
 │       └── ... (testes)
 ├── pom.xml
 └── README.md
```

## Exemplos de Uso

### Cadastro de Item

```json
POST /api/itens
{
  "nome": "Notebook Dell",
  "categoria": "Eletrônicos",
  "quantidade": 10,
  "descricao": "Notebook para uso administrativo"
}
```

### Movimentação de Estoque

```json
POST /api/movimentacoes
{
  "itemId": 1,
  "tipo": "SAIDA",
  "quantidade": 2,
  "observacao": "Saída para manutenção"
}
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## Licença

Este projeto está licenciado sob a licença MIT.

---

> Feito com Java e Spring Boot para facilitar o controle de inventários.
