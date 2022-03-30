import java.util.Scanner;

public class CLI {

    private Scanner reader;
    private User user;

    public CLI() {
        this.reader = new Scanner(System.in);
    }

    public void options() {
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
            int option = this.reader.nextInt();
            switch (option) {
                case 1:
                    this.editProfile();
                    break;
                case 2:
                    this.viewProfile();
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
        if (this.user != null) {
            if (this.user.pwd.equals(pwd)) {
                this.user.setLogged(true);
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
}