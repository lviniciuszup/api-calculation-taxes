#  API Gerenciamento de Impostos
API de Gerenciamento de Impostos! Projeto desenvolvido para gerenciar tipos de impostos, realizar cálculos e gerenciar usuários com autenticação baseada em JWT. 🚀
---
## 🛠️ **Tecnologias Utilizadas**
- **Java 17** 
- **Spring Boot** 
- **Spring Security** 
- **JWT (JSON Web Token)** 
- **Swagger/OpenAPI** 
- **Jakarta Validation** 
- **Banco de Dados Relacional** 🗄

---

## 📋 **Funcionalidades**

### **Autenticação e Autorização**
- Autenticação baseada em **JWT**.
- Controle de acesso por **roles** (`ADMIN`, `USER`).
- Endpoints protegidos para diferentes níveis de acesso.

### **Gerenciamento de Impostos**
- **CRUD** de impostos.
- Cálculo de impostos com os valores fornecidos.

### **Gerenciamento de Usuários**
- Registro de novos usuários.
- Login com geração de token JWT.
  
---
## 🚀 **Executando o Projeto**

1. **Java 17** instalado.
2. **Maven** instalado.
3. Banco de dados configurado (PostgreSQL).
4. Variáveis de ambiente configuradas:
- `DB_USERNAME`: Nome de usuário do banco de dados.
- `DB_URL`: URL de conexão com o banco de dados.
- `DB_PASSWORD`: Senha do banco de dados.

### **Rodando o projeto**
1. Clone o repositório:
git clone https://github.com/seu-usuario/api-calculation-taxes
cd api-calculation-taxes
```
2. Configure o arquivo `application.properties` com as credenciais do banco de dados:
spring.datasource.url=jdbc:mysql://localhost:3306/api-calculation-taxes
spring.datasource.username=seu-admin
spring.datasource.password=sua-admin
```
3. Execute o projeto:

mvn spring-boot:run
```
## 🔑 **Autenticação**
A autenticação é feita pelo **JWT**. Para acessar os endpoints, siga os passos:

1. **Registre um usuário**:
- Endpoint: `POST /users`
- Exemplo de payload:
```json
{
"username": "admin",
"password": "123456",
"roles": ["ADMIN"]
}
```
2. **Faça login**:
- Endpoint: `POST /users/login`
- Exemplo de payload:
```json
{
"username": "admin",
"password": "123456"
}
```
- Resposta:
```json
{
"token": "eyJhbGciOiJIUzI1NiIsbnhj89InR5clkmkljnklCI6IkpXVCJ9..."
}
```
3. Use o token JWT no cabeçalho das requisições:
```http
Authorization: Bearer <seu-token>
```

### Exemplos de Endpoints


#### **1. Criar um Tipo de Imposto**
- **Método**: `POST`
- **URL**: `/taxes/tipos`
- **Payload**:
```json
{
  "name": "IR",
  "description": "Imposto sobre renda",
  "aliquot": 10.0
}
```
- **Resposta**:
```json
{
"id": 3,
"name": "IR",
"description": "Imposto sobre renda",
"aliquot": 10.0
}
```
#### **2. Calcular Imposto**
- **Método**: `POST`
- **URL**: `/taxes/calculo`
- **Payload**:
```json
{
"taxId": 1,
"baseValue": 1000.0
}
```
- **Resposta**:
```json
{
"taxName": "ICMS",
"baseValue": 1000.0,
"rate": 18.0,
"calculatedValue": 180.0
}
```
#### **3. Listar todos os Tipos de Impostos**
- **Método**: `GET`
- **URL**: `/taxes/tipos`
- **Resposta**:
```json
[
{
"id": 1,
"name": "IR",
"rate": 15.0
},
{
"id": 2,
"name": "ISS",
"rate": 3.0
}
]
```

#### **2. Listar Impostos por ID**
- **Método**: `GET`
- **URL**: `/taxes/tipos/1`
- **Resposta**:
```json
[
{
"id": 1,
"name": "IR",
"description": "Imposto sobre renda",
"aliquot": 15.0
}

```

## 🛡️ **Segurança**
- **JWT** usado para autenticação e autorização.


