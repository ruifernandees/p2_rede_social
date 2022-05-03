import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner reader;
    private IUserDB db;
    private User user;
    private int currentUserIndex;
    private Feed feed;
    private ArrayList<Community> communities;

    public CLI(IUserDB db) {
        this.reader = new Scanner(System.in);
        this.db = db;
        this.feed = new Feed();
        this.communities = new ArrayList<Community>();
        this.communities.add(new Community("José", "Comunidade do bairro", "Uma descrição"));
        this.communities.add(new Community("João", "Os gamers", "Outra descrição"));
    }

    public void options() {
        this.reader = new Scanner(System.in);
        System.out.println("=== IFACE ===");
        Boolean exitCLI = false;
        if (this.user == null) {
            System.out.println("[1] Entrar");
            System.out.println("[2] Cadastrar");
            System.out.println("[101] Sair do IFace");
            System.out.print("=> ");
            int option = this.reader.nextInt();
            switch (option) {
                case 1:
                    this.loginInterface();
                    break;
                case 2:
                    this.signUp();
                    break;
                case 101:
                    exitCLI = true;
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("[1] Editar Perfil");
            System.out.println("[2] Mostrar Perfil");
            System.out.println("[3] Abrir feed");
            System.out.println("[4] Enviar mensagem no feed");
            System.out.println("[5] Todos usuários da rede");
            System.out.println("[6] Enviar solicitação de amizade (interativo)");
            System.out.println("[7] Ver solicitações de amizade (interativo)");
            System.out.println("[8] Ver amigos");
            System.out.println("[9] Ver inbox");
            System.out.println("[10] Enviar mensagem para alguém");
            System.out.println("[11] Mandar mensagem para comunidades");
            System.out.println("[12] Entrar em comunidades");
            System.out.println("[13] Criar comunidade");
            System.out.println("[100] Encerrar sessão");
            System.out.println("[101] Sair do IFace");
            System.out.println("[102] Remover a conta");
            System.out.print("=> ");
            int option = this.reader.nextInt();
            switch (option) {
                case 1:
                    this.editProfile();
                    break;
                case 2:
                    this.viewProfile();
                    break;
                case 3:
                    System.out.println("=== Feed ===");
                    this.feed.show(currentUserIndex, this.db);
                    break;
                case 4:
                    this.sendMessageToFeed();
                    break;
                case 5:
                    this.db.showAllUsers();
                    break;
                case 6:
                    this.sendFriendRequest();
                    break;
                case 7:
                    this.showFriendsRequests();
                    break;
                case 8:
                    this.showFriends();
                    break;
                case 9:
                    this.showInbox();
                    break;
                case 10:
                    this.sendMessageToInbox();
                    break;
                case 11:
                    this.showCommunity();
                    break;
                case 12:
                    this.enterCommunity();
                    break;
                case 13:
                    this.createCommunity();
                    break;
                case 100:
                    this.logout();
                    break;
                case 101:
                    exitCLI = true;
                    break;
                case 102:
                    this.removeUser();
                    break;
                default:
                    break;
            }
        }
        if (!exitCLI) {
            this.options();
        }
    }

    public void loginInterface() {
        System.out.print("Informe o login: ");
        String login = reader.next();
        System.out.print("Informe a senha: ");
        String pwd = reader.next();
        this.login(login, pwd);
    }

    public void login(String login, String pwd) {
        User userToLogin = null;
        int currentUserIndex = -1;
        for (int i = 0; i < this.db.amountOfUsers(); i++) {
            User currentUser = this.db.getUser(i);
            if (currentUser.login.equals(login)) {
                userToLogin = currentUser;
                currentUserIndex = i;
                break;
            }
        }
        if (userToLogin != null) {
            if (userToLogin.pwd.equals(pwd)) {
                if (userToLogin.isActivated) {
                    this.user = userToLogin;
                    this.currentUserIndex = currentUserIndex;
                    System.out.println("Login realizado com sucesso!");
                } else {
                    System.out.println("Usuário apagado!");
                }
            } else {
                System.out.println("Senha inválida!");
            }
        } else {
            System.out.println("Crie uma conta para poder logar!");
        }
    }

    public User signUp() {
        System.out.print("Informe o nome de usuário: ");
        String username = reader.next();
        System.out.print("Informe o login: ");
        String login = reader.next();
        System.out.print("Informe a senha: ");
        String pwd = reader.next();
        User user = new User(username, login, pwd, new ArrayList<Integer>(), new ArrayList<Integer>());
        this.db.addUser(user);
        this.login(login, pwd);
        return user;
    }  

    public void viewProfile() {
        System.out.println("\n" + this.user + "\n");
        this.showUserCommunities();
        System.out.println();
    }
    
    public User editProfile() {
        System.out.println("Edição perfil:");
        System.out.println("Novo nome [digite \"N\" caso queira manter o mesmo]");
        String newName = this.reader.next();
        if (!newName.equals("N")) {
            this.user.username  = newName;
        }
        System.out.println("Novo login [digite \"N\" caso queira manter o mesmo]");
        String newLogin = this.reader.next();
        if (!newLogin.equals("N")) {
            this.user.login  = newLogin;
        }
        System.out.println("Nova senha [digite \"N\" caso queira manter a mesma senha]");
        String newPwd = this.reader.next();
        if (!newPwd.equals("N")) {
            this.user.pwd  = newPwd;
        }
        System.out.println("Mostrar mensagens do feed apenas para amigos? [S/N]");
        String result = this.reader.next();
        if (result.equals("S")) {
            this.user.feedMessageOnlyForFriends = true;
        } else if (result.equals("N")) {
            this.user.feedMessageOnlyForFriends = false;
        }
        System.out.println("Dados atualizados com sucesso!");
        return this.user;
    }

    public void removeUser() {
        String username = this.user.username;
        for (Community community : this.communities) {
            community.users.removeIf(user -> user.equals(username));
            if (community.ownerUsername.equals(username)) {
                this.communities.remove(community);
            }
        }
        for (User user : this.db.getAllUsers()) {
            user.friendsIndexes.removeIf(friend -> friend.equals(currentUserIndex));
            user.requestingYourFriendshipIndexes.removeIf(friendRequest -> friendRequest.equals(currentUserIndex));
            user.inbox.conversations.removeIf(conversation -> conversation.getFirstUser().equals(username) || conversation.getSecondUser().equals(username));
        }
        this.db.getUser(currentUserIndex).isActivated = false;
        this.user = null;
        this.currentUserIndex = -1;
        this.feed.messages.removeIf(message -> message.username.equals(username));
        System.out.println("Sua conta foi removida do IFace, " + username + ".");
    }

    public void sendMessageToFeed() {
        this.reader.nextLine();
        System.out.println("Nova mensagem: ");
        String message = this.reader.nextLine();
        System.out.println(message);
        this.feed.addMessage(new Message(message, this.user.username));
    }

    public void createCommunity() {
        this.reader.nextLine();
        System.out.println("Nome da comunidade: ");
        String name = this.reader.nextLine();
        System.out.println("Descrição da comunidade: ");
        String description = this.reader.nextLine();
        this.communities.add(new Community(this.user.username, name, description));
        System.out.println("Comunidade criada com sucesso!");
    }

    public void enterCommunity() {
        this.showAllCommunities();
        System.out.println("Informe o número da comunidade: ");
        Integer number = this.reader.nextInt() - 1;
        if (number < 0 || number >= this.communities.size()) {
            System.out.println("Comunidade inválida!");
            return;
        }
        if (this.communities.get(number).users.contains(this.user.username)) {
            System.out.println("Você já está nessa comunidade!");
            return;
        }
        this.communities.get(number).addUser(this.user.username);
        System.out.println("Você entrou na comunidade " + this.communities.get(number).name + ".");
    }

    public void showUserCommunities() {
        System.out.println("==== SUAS COMUNIDADES ====");
        for (int i = 0; i < this.communities.size(); i++) {
            Community community = this.communities.get(i);
            if (!community.users.contains(this.user.username)) {
                continue;
            }
            System.out.println(community.name);
            System.out.println("  => " + community.description);
        }
    }

    public void showCommunity() {
        this.showAllCommunities();
        System.out.println("Informe o número da comunidade: ");
        Integer number = this.reader.nextInt() - 1;
        if (number < 0 || number >= this.communities.size()) {
            System.out.println("Comunidade inválida!");
            return;
        }
        this.communities.get(number).show();
        this.reader.nextLine();
        System.out.println("Nova mensagem: [N, para sair] ");
        String message = this.reader.nextLine();
        if (message.equals("N")) {
            return;
        }
        System.out.println(message);
        this.communities.get(number).content.addMessage(new Message(message, this.user.username));
    }

    public void showAllCommunities() {
        System.out.println("==== COMUNIDADES ====");
        for (int i = 0; i < this.communities.size(); i++) {
            Community community = this.communities.get(i);
            System.out.println("[" + (i + 1) + "]" + community.name);
            System.out.println("  => " + community.description);
        }
    }

    public void logout() {
        System.out.println("Até mais, " + this.user.username + "!");
        this.db.getUser(currentUserIndex).setLogged(false);
        this.currentUserIndex = -1;
        this.user = null;
    }

    public void sendFriendRequest() {
        for (int i = 0; i < this.db.amountOfUsers(); i++) {
            User currentUser = this.db.getUser(i);
            if (i == this.currentUserIndex) continue;
            if (this.user.friendsIndexes.contains(i)) continue;
            if (!this.db.getUser(i).isActivated) continue;
            if (this.db.getUser(i).requestingYourFriendshipIndexes.contains(this.currentUserIndex)) continue;
            System.out.println(i + ". " + currentUser.username + ". Adicionar [S/N]");
            String response = this.reader.next();
            if (response.equals("S")) {
                if (this.user.requestingYourFriendshipIndexes.contains(i)) {
                    this.user.friendsIndexes.add(i);
                    this.db.getUser(i).friendsIndexes.add(this.currentUserIndex);
                    System.out.println(currentUser.username + " adicionado, pois ele já havia requisitado a você!");
                    Integer indexC = i;
                    this.user.requestingYourFriendshipIndexes.removeIf(index -> index == indexC);
                    continue;
                }
                this.db.getUser(i).requestingYourFriendshipIndexes.add(this.currentUserIndex);
                System.out.println("Pedido feito a " + currentUser.username + " com sucesso!");
            }
        }
    }

    public void showFriendsRequests() {
        int amountOfRequests = this.user.requestingYourFriendshipIndexes.size();
        System.out.println(this.user.requestingYourFriendshipIndexes);
        if (amountOfRequests == 0) {
            System.out.println("\nNão há solicitações de amizade no momento!\n");
            return;
        }
        ArrayList<Integer> addedUsersIndexes = new ArrayList<Integer>();
        for (int i = 0; i < amountOfRequests; i++) {
            System.out.println(this.user.requestingYourFriendshipIndexes);
            Integer currentUserLoopIndex = this.user.requestingYourFriendshipIndexes.get(i);
            System.out.println(currentUserLoopIndex);
            User currentUser = this.db.getUser(currentUserLoopIndex);
            System.out.println(currentUser);
            System.out.println(i + ". " + currentUser.username + ". Aceitar? [S/N]");
            String response = this.reader.next();
            if (response.equals("S")) {
                this.user.friendsIndexes.add(currentUserLoopIndex);
                this.db.getUser(currentUserLoopIndex).friendsIndexes.add(this.currentUserIndex);
                System.out.println(currentUser.username + " adicionado!");
                addedUsersIndexes.add(i);
            } else if (response.equals("N")) {
                System.out.println(currentUser.username + " não adicionado!");
            }
        }
        
        for (int j = 0; j < addedUsersIndexes.size(); j++) {
            this.db.getUser(this.currentUserIndex).requestingYourFriendshipIndexes.remove(0);
        }
        System.out.println(this.db.getUser(this.currentUserIndex).requestingYourFriendshipIndexes);
    }

    public void showFriends() {
        System.out.println("\n=== Amigos ===");
        for (int i = 0; i < this.user.friendsIndexes.size(); i++) {
            Integer currentLoopIndex = this.user.friendsIndexes.get(i);
            System.out.println(this.db.getUser(currentLoopIndex));
        }
    }

    public void showInbox() {
        this.user.showInbox();
    }

    public void sendMessageToInbox() {
        System.out.println("=> Mensagem para (username): ");
        String to = this.reader.next();
        this.reader.nextLine();
        System.out.print("=> Conteúdo: ");
        String content = this.reader.nextLine();
        Message message = new Message(content, this.user.username);
        for (int i = 0; i < this.db.amountOfUsers(); i++) {
            if (this.db.getUser(i).username.equals(to)) {
                this.db.getUser(i).inbox.addMessage(message, to);
                this.db.getUser(this.currentUserIndex).inbox.addMessage(message, to);
                System.out.println("Mensagem enviada para " + to + "!");
                return;
            }
        }
    }
}