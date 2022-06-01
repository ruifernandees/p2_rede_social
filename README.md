## Exceções implementadas/tratadas
- NullPointerException:
  - Ao tentar logar com uma conta que não existe;
- SignUpException:
  - Caso em que o nome de usuário possui caracteres inválidos;
  - Caso em que o login possui espaços em branco ou caracteres inválidos;
  - Caso em que o login já cadastrado em outra conta;
  - Caso em que a senha possui espaços em branco ou é vazia.
- RemoveAccountException:
  - Caso em que a senha informada, a fim de remover a conta, é incorreta;
- IllegalArgumentException;
  - Ao tentar editar o perfil e passar uma opção inválida;
  - Ao tentar mudar senha para uma com espaços em branco e vazia;
  - Ao tentar mudar o login, sendo que existe um usuário com login igual;
  - Ao tentar mudar o login para um com espaços em branco ou com caracteres inválidos;
  - Ao tentar mudar o nome de usuário para um com caracteres inválidos;
  - Ao tentar enviar mensagem para o inbox de um usuário inexistente;
  - Ao dar uma resposta inválida na interface de enviar pedido de amizade;
  - Ao dar uma resposta inválida na interface de mostrar pedidos de amizade;
- InputMismatchException:
  - Ao tentar recuperar informações do perfil, colocando input inválido;
- IndexOutOfBoundsException:
  - Ao tentar entrar em comunidade inexistente;
  - Ao tentar enviar mensagem para comunidade inexistente;
- CommunityException
  - Ao tentar criar comunidade com o mesmo nome de uma já existente;
- MessageException
  - Ao tentar enviar uma mensagem no feed com palavras proibidas

## Como rodar
```bash
$ cd src
$ javac IFace.java
$ java IFace
```
