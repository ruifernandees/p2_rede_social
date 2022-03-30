import java.util.ArrayList;
import java.util.Scanner;

public class CLI {

    private Scanner reader;
    private UserDB db;
    private User user;
    private int currentUserIndex;
    private Feed feed;

    public CLI() {
        this.reader = new Scanner(System.in);
        this.db = new UserDB();
        this.feed = new Feed();
    }

    public void options() {
        this.reader = new Scanner(System.in);
        System.out.println("=== IFACE ===");
        Boolean exitCLI = false;
        if (this.user == null) {
            System.out.println("[1] Entrar");
            System.out.println("[2] Cadastrar");
            System.out.println("[100] Sair do IFace");
            System.out.print("=> ");
            int option = this.reader.nextInt();
            switch (option) {
                case 1:
                    this.login();
                    break;
                case 2:
                    this.signUp();
                    break;
                case 100:
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
            System.out.println("[9] Encerrar sessão");
            System.out.println("[10] Remover a conta");
            System.out.println("[100] Sair do IFace");
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
                    this.feed.show();
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
                    this.logout();
                    break;
                case 10:
                    this.removeUser();
                    break;
                case 100:
                    exitCLI = true;
                    break;
                default:
                    break;
            }
        }
        if (!exitCLI) {
            this.options();
        }
    }

    public void login() {
        System.out.print("Informe o login: ");
        String login = reader.next();
        System.out.print("Informe a senha: ");
        String pwd = reader.next();
        User userToLogin = null;
        int currentUserIndex = -1;
        for (int i = 0; i < this.db.users.size(); i++) {
            User currentUser = this.db.users.get(i);
            if (currentUser.login.equals(login)) {
                userToLogin = currentUser;
                currentUserIndex = i;
                break;
            }
        }
        if (userToLogin != null) {
            if (userToLogin.pwd.equals(pwd)) {
                this.user = userToLogin;
                this.user.setLogged(true);
                this.currentUserIndex = currentUserIndex;
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
        this.user = new User(username, login, pwd, new ArrayList<Integer>(), new ArrayList<Integer>());
        this.viewProfile();
        this.db.addUser(user);
        return user;
    }  

    public void viewProfile() {
        System.out.println("\n" + this.user + "\n");
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
        System.out.println("Dados atualizados com sucesso!");
        return this.user;
    }

    public void removeUser() {
        String username = this.user.username;
        this.db.users.remove(currentUserIndex);
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

    public void logout() {
        System.out.println("Até mais, " + this.user.username + "!");
        this.db.users.get(currentUserIndex).setLogged(false);
        this.currentUserIndex = -1;
        this.user = null;
    }

    public void sendFriendRequest() {
        for (int i = 0; i < this.db.users.size(); i++) {
            User currentUser = this.db.users.get(i);
            if (i == this.currentUserIndex) continue;
            if (this.user.friendsIndexes.contains(i)) continue;
            if (this.db.users.get(i).requestingYourFriendshipIndexes.contains(this.currentUserIndex)) continue;
            System.out.println(i + ". " + currentUser.username + ". Adicionar [S/N]");
            String response = this.reader.next();
            if (response.equals("S")) {
                if (this.user.requestingYourFriendshipIndexes.contains(i)) {
                    this.user.friendsIndexes.add(i);
                    this.db.users.get(i).friendsIndexes.add(this.currentUserIndex);
                    System.out.println(currentUser.username + " adicionado, pois ele já havia requisitado a você!");
                    Integer indexC = i;
                    this.user.requestingYourFriendshipIndexes.removeIf(index -> index == indexC);
                    continue;
                }
                this.db.users.get(i).requestingYourFriendshipIndexes.add(this.currentUserIndex);
                System.out.println("Pedido feito a " + currentUser.username + " com sucesso!");
            }
        }
    }

    public void showFriendsRequests() {
        int amountOfRequests = this.user.requestingYourFriendshipIndexes.size();
        if (amountOfRequests == 0) {
            System.out.println("\nNão há solicitações de amizade no momento!\n");
            return;
        }
        ArrayList<Integer> addedUsersIndexes = new ArrayList<Integer>();
        for (int i = 0; i < amountOfRequests; i++) {
            System.out.println(this.user.requestingYourFriendshipIndexes);
            Integer currentUserLoopIndex = this.user.requestingYourFriendshipIndexes.get(i);
            System.out.println(currentUserLoopIndex);
            User currentUser = this.db.users.get(currentUserLoopIndex);
            System.out.println(currentUser);
            System.out.println(i + ". " + currentUser.username + ". Aceitar? [S/N]");
            String response = this.reader.next();
            if (response.equals("S")) {
                this.user.friendsIndexes.add(currentUserLoopIndex);
                this.db.users.get(currentUserLoopIndex).friendsIndexes.add(this.currentUserIndex);
                System.out.println(currentUser.username + " adicionado!");
                addedUsersIndexes.add(i);
            } else if (response.equals("N")) {
                System.out.println(currentUser.username + " não adicionado!");
            }
        }
        
        for (int j = 0; j < addedUsersIndexes.size(); j++) {
            this.db.users.get(this.currentUserIndex).requestingYourFriendshipIndexes.remove(0);
        }
        System.out.println(this.db.users.get(this.currentUserIndex).requestingYourFriendshipIndexes);
    }

    public void showFriends() {
        System.out.println("\n=== Amigos ===");
        for (int i = 0; i < this.user.friendsIndexes.size(); i++) {
            Integer currentLoopIndex = this.user.friendsIndexes.get(i);
            System.out.println(this.db.users.get(currentLoopIndex));
        }
    }
}