package presentation.cli.strategies;

import java.util.InputMismatchException;
import java.util.Scanner;

import domain.exceptions.LoginException;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.LoginUseCase;
import presentation.cli.CLIConstants;
import presentation.cli.ICLIStrategy;

public class AuthenticationCLIStrategy implements ICLIStrategy {

    private Scanner reader;

    public AuthenticationCLIStrategy() {
        this.reader = new Scanner(System.in);
    }

    @Override
    public boolean execute() {
        System.out.println("[1] Entrar");
        System.out.println("[2] Cadastrar");
        System.out.println("[101] Sair do IFace");
        System.out.print("=> ");
        int option = -1;
        try {
            option = this.reader.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!");
            return CLIConstants.RUN_CLI;
        }
        switch (option) {
            case 1:
                this.login();
                return CLIConstants.RUN_CLI; 
            case 2:
                this.signup();
                return CLIConstants.RUN_CLI; 
            case 101:
                return CLIConstants.CLOSE_CLI;
            default:
                return CLIConstants.RUN_CLI; 
        }
    }

    private void login() {
        System.out.print("Informe o login: ");
        String login = reader.next();
        System.out.print("Informe a senha: ");
        String pwd = reader.next();
        try {
            LoginUseCase loginUseCase = new LoginUseCase(new MemoryUsersRepository());
            loginUseCase.execute(login, pwd);
        } catch (LoginException e) {
            System.out.println(e.getMessage());
        }
    }

    private void signup() {
        // try {
        // } catch (SignUpException e) {
        //     System.out.println(e.getMessage());
        // }
    }
}
