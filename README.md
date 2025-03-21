# DESAFIO SENIOR

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.3**
- **Banco de Dados Oracle**
- **Maven**

### Ordem de Execução das Procedures

Antes de iniciar o sistema, execute as seguintes procedures na ordem correta:

1. **PROC\_INICIALIZAR\_BANCO** – Cria e popula tabelas essenciais.
2. **PROC\_CONFIGURAR\_PARAMETROS** – Define configurações iniciais do sistema.
3. **PROC\_OBTER\_CIDADES\_MAIS\_DISTANTES** – Procedure responsável por calcular as cidades mais distantes entre si.

Execute cada procedure no banco Oracle utilizando um cliente SQL ou via script automatizado.

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

