# Bankslip application
### Pre-requisitos:
> - Java 8
> - Apache Maven - v3.3.9*
> - Docker - v17.05.0-ce*
> - Docker-Compose - v1.8.0*

### Para utilizar a aplicação:

Compilar o projeto: 
```
mvn clean package docker:build
```
Executar o arquivo: 'docker-compose.yml' com o comando:
```
 docker-compose --file docker-compose.yml up
```
Para incluir um novo boleto
```
curl -H "Content-Type: application/json" -d '{"due_date":"2018-01-01","total_in_cents":"100000","customer":"Trillian Company"}' http://localhost:8080/rest/bankslips

```
Para listar boletos
```
curl http://localhost:8080/rest/bankslips
```
Para ver detalhes de um boleto
```
curl http://localhost:8080/rest/bankslips/**<código de um boleto válido>**
```
Para pagar um boleto
```
curl -H "Content-Type: application/json" -X PUT -d '{"status":"PAID"}' http://localhost:8080/rest/bankslips/**<código de um boleto válido>**
```
Para cancelar um boleto
```
curl -H "Content-Type: application/json" -X PUT -d '{"status":"CANCELED"}' http://localhost:8080/rest/bankslips/**<código de um boleto válido>**