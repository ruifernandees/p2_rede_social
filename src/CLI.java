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
        if (this.user == null) {
            System.out.println("[1] Entrar");
            System.out.println("[2] Cadastrar");
            System.out.print("=> ");
            int option = this.reader.nextInt();
            switch (option) {
                case 1:
                    this.login();
                    break;
                case 2:
                    this.signUp();
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
            System.out.println("[6] Sair");
            System.out.println("[7] Remover a conta");
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
                    this.logout();
                    break;
                case 7:
                    this.removeUser();
                    break;
                default:
                    break;
            }
        }
        this.options();
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
        this.user = new User(username, login, pwd);
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
        this.user = null;
    }
}