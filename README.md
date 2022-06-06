## Code smells
- CLI possui várias responsabilidades e métodos que não fazem parte do escopo dela, que seria apenas fazer uma interface entre o usuário e a aplicação (Large class);
- CLI possui muitas referências a atributos e métodos de outros objetos (Feature envy);
- A maioria dos métodos de CLI possuem função tanto de fazer I/O quanto de lidar com regras de negócio (long methods);
- Método userExists em ProfileMatcher possui muitas referências a dados de outro objeto - db (Feature envy);
- A classe User possui métodos que são responsabilidades da classe Inbox e fazem referências a seus dados(Feature envy).

## Como rodar
```bash
$ cd src
$ javac IFace.java
$ java IFace
```
