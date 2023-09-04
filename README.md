# Login_KLM

## Configurar banco de dados Local

Em src/main/resources/application.properties, você deve atualizar as linhas 1, 2 e 3 para a configuração do seu banco:  

spring.datasource.url=jdbc:mariadb://localhost:**Sua_Porta**/**Nome_do_banco**  
spring.datasource.username=**Usuario_do_banco**  
spring.datasource.password=**Senha_do_banco**  


#
## Utilizar o banco de dados já criado

Em src/main/resources/application.properties, você deve subtituir, na linha 1, "localhost" pelo IP que hospedo o banco:  

spring.datasource.url=jdbc:mariadb://26.30.220.241:3306/lkmlogin

