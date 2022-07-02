package presentation.cli.strategies;

import java.util.InputMismatchException;

import domain.dtos.CreateUserDTO;
import domain.exceptions.LoginException;
import domain.exceptions.SignUpException;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.usecases.LoginUseCase;
import domain.usecases.SignUpUseCase;
import presentation.cli.CLIConstants;
import presentation.cli.CLIStrategy;

public class AuthenticationCLIStrategy extends CLIStrategy {

    public AuthenticationCLIStrategy() {
        super();
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
        System.out.print("Informe o nome de usuário: ");
        reader.nextLine();
        String username = reader.nextLine();
        System.out.print("Informe o login: ");
        String login = reader.nextLine();
        System.out.print("Informe a senha: ");
        String password = reader.nextLine();
        SignUpUseCase signUpUseCase = new SignUpUseCase(new MemoryUsersRepository());
        try {
            signUpUseCase.execute(new CreateUserDTO(username, login, password));
            System.out.println("Conta criada com sucesso! Faça login.");
        } catch (SignUpException e) {
            System.out.println(e.getMessage());
        }
    }
}
