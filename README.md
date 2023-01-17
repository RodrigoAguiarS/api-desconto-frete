# Projeto de Cálculo de Frete
Este projeto é uma aplicação de exemplo que calcula o valor do frete de acordo com o peso, CEP de origem e CEP de destino.

## Tecnologias Utilizadas
* Java 11
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Maven
* PostgreSQL

## Como usar
1. Faça o clone do repositório: git clone https://github.com/seu-usuario/calculo-frete.git
2. Acesse o diretório do projeto: cd calculo-frete
3. Execute o comando mvn clean install para baixar as dependências e fazer o build do projeto
4. Crie um banco de dados PostgreSQL com o nome "calculo_frete"
5. Altere as configurações de conexão com o banco de dados no arquivo src/main/resources/application.properties
6. Inicie a aplicação com o comando mvn spring-boot:run
7. Acesse a URL http://localhost:8080/frete para calcular o frete
8. Utilize o payload abaixo como exemplo de requisição:

* Foi utilizado Api do ViaCep para consultar os ceps.
```javascript
{
    "peso": 100.0,
    "cepOrigem": "12010050",
    "cepDestino": "02311130",
    "nomeDestinatario": "Cidade Igual DDD diferente"
}
```

## Padrões de projeto utilizados
* DTO (Data Transfer Object)
* Service
* Repository
* Strategy

Este projeto é uma aplicação de cálculo de frete, que tem como objetivo calcular o valor do frete com base no peso, CEP de origem e CEP de destino.

Foi utilizado o padrão DTO (Data Transfer Object) para garantir a separação entre as camadas de dados e lógica da aplicação. Isso foi feito através da criação de uma classe FreteDto, que contém somente os atributos necessários para realizar o cálculo do frete.

O padrão Service foi utilizado para organizar as regras de negócio da aplicação. A classe FreteService contém os métodos responsáveis por realizar o cálculo do frete e salvar os dados no banco de dados.

Por fim, foi utilizado o padrão Strategy para definir as regras de cálculo do frete de acordo com o estado e DDD de origem e destino. Isso foi feito através da criação de classes concretas (EstadosDDDIguais, EstadosIguaisDDDNaoe EstadosDiferentes) que implementam a lógica específica de cada situação, e uma classe abstrata (Verificador) que é responsável por encadear as classes concretas e passar os dados para a próxima verificação
