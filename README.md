# Linketinder - Sistema de Contratação de Funcionários

O Linketinder é um sistema de contratação de funcionários inspirado no Linkedin e no Tinder, desenvolvido em Groovy. Ele permite cadastrar candidatos (Pessoa Física) e empresas (Pessoa Jurídica) com suas respectivas informações e competências. Além disso, oferece a funcionalidade de encontrar candidatos compatíveis com as competências desejadas.

## Funcionalidades

- Listar empresas cadastradas
- Listar candidatos cadastrados
- Cadastrar novo candidato (Pessoa Física)
- Cadastrar nova empresa (Pessoa Jurídica)
- Encontrar match de candidatos com base nas competências desejadas
- Sair do programa

## Funcionalidade de Curtidas e Matches

Uma nova funcionalidade foi adicionada ao Linketinder, permitindo que candidatos e empresas possam curtir vagas e perfis de maneira similar ao sistema de curtidas do aplicativo Tinder. Quando um candidato curte uma vaga, e a empresa correspondente curte o perfil do candidato de volta, ocorre um evento de match, abrindo a possibilidade de comunicação e troca de informações entre as duas partes.

### Exemplo de Uso:

1. Candidato curte uma vaga:
   - O candidato pode curtir uma vaga disponível, demonstrando interesse na posição.

2. Empresa curte um candidato:
   - Se a empresa também curtir o perfil do candidato, ocorre um evento de match.

3. Match:
   - Quando ocorre um match entre um candidato e uma empresa, a funcionalidade de match é ativada.
   - As informações sobre o match são registradas internamente na aplicação.

### Como Funciona Internamente:

Internamente na aplicação, os matches são representados através da classe `Curtida`, que contém referências tanto ao candidato quanto à empresa envolvidos no matc

## Como Executar

1. Certifique-se de ter o Groovy instalado em sua máquina. Você pode baixá-lo [aqui](https://groovy.apache.org/download.html) e seguir as instruções de instalação.

2. Clone o repositório do projeto:
git clone [https://github.com/seu-usuario/linketinder.git](https://github.com/Gabriel-Almeida00/ZG-HERO-Project.git)

3. Navegue para o diretório do projeto:
cd linketinder

4. Execute o programa:
groovy Main.groovy


5. Siga as instruções no terminal para usar as diferentes funcionalidades do programa.

   # Banco de Dados do Linketinder

Este repositório contém a modelagem de banco de dados para o Linketinder, um aplicativo de matchmaking entre candidatos e empresas. O banco de dados foi implementado usando PostgreSQL e o modelo foi criado usando a ferramenta dbdiagram.io.

## Modelo de Entidade-Relacionamento (MER)

O modelo de entidade-relacionamento (MER) para o banco de dados do Linketinder foi projetado para acomodar os perfis de candidatos, empresas, competências, formações, experiências e vagas. A modelagem inclui relações entre essas entidades, permitindo a interação entre candidatos e empresas por meio de curtidas em vagas e perfis.

O modelo pode ser visualizado em detalhes no arquivo [linketinder_model.png](https://github.com/Gabriel-Almeida00/ZG-HERO-Project/blob/main/src/sql/linketinder.pdf).

Script sql para criação:([https://github.com/Gabriel-Almeida00/ZG-HERO-Project/blob/main/src/sql/linketinder.pdf](https://github.com/Gabriel-Almeida00/ZG-HERO-Project/blob/main/src/sql/linketinder).

## PostgreSQL

O banco de dados foi implementado usando o sistema de gerenciamento de banco de dados PostgreSQL. O PostgreSQL é uma escolha sólida para sistemas relacionais, oferecendo robustez, escalabilidade e recursos avançados.





## Contribuição

Se você quiser contribuir para o projeto, fique à vontade para abrir um pull request. Qualquer ajuda é bem-vinda!

