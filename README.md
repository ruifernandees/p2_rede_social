# Refatoração do Projeto - Aplicação de Design Patterns
- Explicarei a refatoração em dois tópicos: Arquitetura e Design Patterns aplicados.
## Arquitetura pós-refatoração
- Domain: camada que possui os componentes relacionados a regras de negócio específicas da aplicação;
    - DTOs: objetos de transferência de dados - servem para transitar dados entre camadas;
    - Entities: entidades do domínio, por exemplo, Community, User, Message etc.;
    - Exceptions: excessões que são lançadas dentro do domínio;
    - Repositories: possuem regras relacionadas ao acesso dos dados e operações de Create, Read, Update e Delete;
    - Singletons (serão explicados no próximo tópico);
    - UseCases: Manipulam entidades e tratam regras de negócio específicas. Além disso, eles fazem chamadas a repositórios, quando necessário.
- Infra: camada relacionada à infraestrutura do projeto, por exemplo, banco de dados;
- Presentation: camada responsável pela apresentação. Dentro dela, temos a CLI;
- Utils: camada com funções úteis dentro da aplicação.

## Design Patterns aplicados
- **Strategy** em CLI, separando em estratégia de autenticação (usuário não logado) e estratégia do app (ou seja, usuário logado);
- **Repository**, para encapsular a lógica de acesso a dados, por meio de Inversão de Dependência; 
- **Singleton**, para evitar inconsistência de dados e para:
  - Criar ponto central de conexão com o banco de dados;
  - Criar provedor de autenticação, que possui informações de usuário logado;
- **Removal of Large Class**, separando a responsabilidade de CLI em:
  - AuthenticationCLIStrategy: CLI da área de autenticação;
  - AppCLIStrategy: CLI da área do app (usuário logado);
  - Controllers: controladores que fazem parte do módulo de command line interface. Cada controlador tem uma responsabilidade específica. Eles tratam o I/O e fazem chamadas por outras classes de camadas diferentes da aplicação. Por exemplo: LoginController trata a parte de login e chama por LoginUseCase, injetando MemoryUsersRepository;
- **Removal of Long Methods** - Os métodos de CLI possuíam múltiplas responsabilidades, que foram separadas em:
  - Strategies;
  - Controllers;
  - UseCases;
  - Repositories;
- **Removal of Long Param list**:
  - Utilizando DTOs e Entities;
- **Removal of Feature Envy**:
  - Havia um método em ProfileMatcher (userExists) que possuía muias referências a dados de outro objeto (db)
    - Foi separada essa responsabilidade para um Repository específico;
  - Classe User possuía métodos que eram responsabilidade da classe Inbox, fazendo muitas referências a seus dados:
    - Essa responsabilidade agora está apenas com Inbox;
  - CLI possuía muitas referências a dados de outras classes (Ex: User, Community, Feed etc.)
    - Aplicando o padrão Singleton e separando as responsabilidades entre classes diferentes, o smell foi resolvido




## Como rodar
```bash
$ cd src
$ javac IFace.java
$ java IFace
```
