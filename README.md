# Refatoração do Projeto - Aplicação de Design Patterns
- Explicarei a refatoração em três tópicos: Code Smells, Arquitetura e Design Patterns aplicados.

## Code smells (Código antes da refatoração)
- Link do código antigo: https://github.com/ruifernandees/p2_rede_social/tree/code-smells
- CLI possui várias responsabilidades e métodos que não fazem parte do escopo dela, que seria apenas fazer uma interface entre o usuário e a aplicação (Large class);
- CLI possui muitas referências a atributos e métodos de outros objetos (Feature envy);
- A maioria dos métodos de CLI possuem função tanto de fazer I/O quanto de lidar com regras de negócio (long methods);
- Método userExists em ProfileMatcher possui muitas referências a dados de outro objeto - db (Feature envy);
- A classe User possui métodos que são responsabilidades da classe Inbox e fazem referências a seus dados(Feature envy).

## Arquitetura pós-refatoração
- [Domain](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain): camada que possui os componentes relacionados a regras de negócio específicas da aplicação;
    - DTOs: objetos de transferência de dados - servem para transitar dados entre camadas;
    - Entities: entidades do domínio, por exemplo, Community, User, Message etc.;
    - Exceptions: excessões que são lançadas dentro do domínio;
    - Repositories: possuem regras relacionadas ao acesso dos dados e operações de Create, Read, Update e Delete;
    - Singletons (serão explicados no próximo tópico);
    - UseCases: Manipulam entidades e tratam regras de negócio específicas. Além disso, eles fazem chamadas a repositórios, quando necessário.
- [Infra](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/infra): camada relacionada à infraestrutura do projeto, por exemplo, banco de dados;
- [Presentation](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation): camada responsável pela apresentação. Dentro dela, temos a CLI;
- [Utils](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/utils): camada com funções úteis dentro da aplicação.

## Design Patterns aplicados
- **Strategy** em CLI, separando em estratégia de autenticação (usuário não logado) e estratégia do app (ou seja, usuário logado). [Link](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation/cli);
- **Repository**, para encapsular a lógica de acesso a dados, por meio de Inversão de Dependência. [Link](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/repositories); 
- **Singleton**, para evitar inconsistência de dados e para:
  - Criar ponto central de conexão com o banco de dados. [Link](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/infra/singletons);
  - Criar provedor de autenticação, que possui informações de usuário logado. [Link](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/singletons);
- **Removal of Large Class**, separando a responsabilidade de [CLI](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation) em:
  - [AuthenticationCLIStrategy](https://github.com/ruifernandees/p2_rede_social/blob/design-patterns/src/presentation/cli/strategies/AuthenticationCLIStrategy.java): CLI da área de autenticação;
  - [AppCLIStrategy](https://github.com/ruifernandees/p2_rede_social/blob/design-patterns/src/presentation/cli/strategies/AppCLIStrategy.java): CLI da área do app (usuário logado);
  - [Controllers](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation/controllers): controladores que fazem parte do módulo de command line interface. Cada controlador tem uma responsabilidade específica. Eles tratam o I/O e fazem chamadas por outras classes de camadas diferentes da aplicação. Por exemplo: LoginController trata a parte de login e chama por LoginUseCase, injetando MemoryUsersRepository;
- **Removal of Long Methods** - Os métodos de CLI possuíam múltiplas responsabilidades, que foram separadas em:
  - [Strategies](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation/cli/strategies);
  - [Controllers](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation/controllers);
  - [UseCases](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/usecases);
  - [Repositories](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/repositories);
- **Removal of Long Param list**:
  - Utilizando [DTOs](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/dtos) e [Entities](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/domain/entities);
- **Removal of Feature Envy**:
  - Havia um método em [ProfileMatcher](https://github.com/ruifernandees/p2_rede_social/blob/design-patterns/src/utils/ProfileMatcher.java) (userExists) que possuía muias referências a dados de outro objeto (db)
    - Foi separada essa responsabilidade para um Repository específico. [Link](https://github.com/ruifernandees/p2_rede_social/blob/design-patterns/src/domain/repositories/implementations/MemoryUsersRepository.java);
  - Classe [User](https://github.com/ruifernandees/p2_rede_social/blob/design-patterns/src/domain/entities/User.java) possuía métodos que eram responsabilidade da classe Inbox, fazendo muitas referências a seus dados:
    - Essa responsabilidade agora está apenas com Inbox;
  - [CLI](https://github.com/ruifernandees/p2_rede_social/tree/design-patterns/src/presentation/cli) possuía muitas referências a dados de outras classes (Ex: User, Community, Feed etc.)
    - Aplicando o padrão Singleton e separando as responsabilidades entre classes diferentes, o smell foi resolvido




## Como rodar
```bash
$ cd src
$ javac IFace.java
$ java IFace
```
