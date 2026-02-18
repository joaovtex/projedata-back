Olá! Este repositório contém a API REST desenvolvida para o desafio técnico de Desenvolvedor Full Stack Jr da Projedata. O sistema foca no controle de estoque de matérias-primas e na sugestão inteligente de produção baseada em valor agregado.

## TÉCNOLOGIAS UTILIZADAS
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- JUnit
- Mockito

## SOBRE O BANCO DE DADOS
O banco de dados utilizado nesse projeto foi feito no MySQL Workbench, e todo o consumo desse banco de dados é feito localmente.

Existe na pasta base desse projeto um arquivo chamado "database.txt", lá estão os códigos de criação do banco e das tabelas que são usadas.

Existem, no total, três tabelas: 
- tb_product: Responsável por armazenar os produtos e seus valores.
- tb_raw_material: Responsável por armazenar as matérias primas e quantidade e estoque.
- tb_p_rm: Responsável por fazer a associação entre produto e matéria prima + armazenar a quantidade de matéria prima necessária para produzir um produto.

## SOBRE O BACK-END (API)
A API foi desenvolvida utilizand Spring Boot.

A API foi construída seguindo uma arquitetura em camadas para garantir a manutenibilidade:
- Controllers: Endpoints da API e manipulação de requisições HTTP.
- DTOs: Padrão para tráfego de dados entre camadas, evitando exposição direta das entidades.
- Entities: Representação das tabelas do banco de dados.
- Exceptions e Validations: Tratamento de erros global e validações de integridade de dados.
- Repositories: Interface de comunicação com o banco de dados via JPA.
- Services: Camada de regras de negócio.

Todos os requisitos funcionais listados relacionados ao back-end estão sendo contemplados nesse projeto.

Inseri também diferentes tipos de validações para evitar o cadastro de dados inconsistentes.

Foram feitas também respostas de erros personalizadas para cada inserção inconcistente.

Nota: Conforme solicitado nos requisitos, toda a codificação e mensagens de retorno da API estão em Inglês.

## SOBRE TESTES
Foram desenvolvidos testes unitários abrangentes para a camada de Service, garantindo que:
- O cálculo de produtos produzíveis esteja correto.
- A priorização por maior valor seja respeitada.
- As validações funcionem corretamente.

