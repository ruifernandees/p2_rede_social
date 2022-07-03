package presentation.cli.strategies;

import java.util.InputMismatchException;

import presentation.cli.CLIConstants;
import presentation.cli.CLIStrategy;
import presentation.controllers.LoginController;
import presentation.controllers.SignUpController;

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
                LoginController.execute(reader);
                return CLIConstants.RUN_CLI; 
            case 2:
                SignUpController.execute(reader);
                return CLIConstants.RUN_CLI; 
            case 101:
                return CLIConstants.CLOSE_CLI;
            default:
                return CLIConstants.RUN_CLI; 
        }
    }
}
