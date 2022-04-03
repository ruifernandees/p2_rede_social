## Classes
- User
- Community
- CommunityContent
- Conversation
- Message
- Inbox
- UserDB
- CLI
- IFace (main)


## Requisitos implementados
1. Criação de Conta - (Método signUp de CLI);
2. Criação/Edição de perfil - (Método signUp e editProfile de CLI (Funcionam para a conta e para o perfil));
3. Adição de amigos - (Métodos sendFriendRequest, showFriendsRequests e showFriends de CLI);
4. Envio de Mensagens - (Método sendMessageToInbox de CLI)
5. Criação de Comunidades - (Métodos createCommunity, showCommunity de CLI)
6. Adição de membros - (Método enterCommunity de CLI)
7. Recuperar Informações sobre um determinado Usuário - (Método viewProfile de CLI)
8. Remoção de Conta - (Método removeUser de CLI)
9. Envio de mensagens no Feed - (Método sendMessageToFeed de CLI);
10. Controle de visualização do Feed de Notícias - (Propriedade feedMessageOnlyForFriends de User)


## Como rodar
```bash
$ cd src
$ javac IFace.java
$ java IFace
```
