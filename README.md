# DESAFIO SENIOR

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.3**
- **Banco de Dados Oracle**
- **Maven**

### Ordem de Execução das Procedures

Antes de iniciar o sistema, execute as seguintes procedures na ordem correta:

1. **1-ESTRUTURA_TABELA_CIDADE.sql**                – Cria e popula tabelas essenciais.
2. **2-PROC_OBTER_ESTADO_MAIOR_MENOR_CIDADES.sql**  – Cria procedure.
3. **3-PROC_OBTER_CIDADES_MAIS_DISTANTES.sql**      – cria procedure.
4. **4-EXECUTAR_APOS_BASE_POPULADA.sql**            – cria indices e atualiza tabela cidade para uso do SDO_GEOMETRY.


## Como Rodar o Projeto

1. Clone o repositório:

2. Configure o banco de dados Oracle no **application.yml**:

3. Compile e execute a aplicação:
   ```sh
   mvn clean install
   mvn spring-boot:run
   ```

## Endpoints Disponíveis

A API expõe os seguintes endpoints principais:

- `http://localhost:8081/swagger-ui/index.html` – Para ter acesso aos endpoints

