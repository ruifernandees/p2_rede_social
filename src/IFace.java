import presentation.cli.CLI;
import presentation.cli.CLIConstants;
import presentation.cli.strategies.AppCLIStrategy;
import presentation.cli.strategies.AuthenticationCLIStrategy;

public class IFace {

    public static void main(String[] args) {
        AuthenticationCLIStrategy authenticationCLIStrategy = new AuthenticationCLIStrategy();
        AppCLIStrategy appCLIStrategy = new AppCLIStrategy();
        CLI cli = new CLI();
        boolean runCLI = CLIConstants.RUN_CLI;
        while (runCLI) {
            if (false) {
                cli.setStrategy(authenticationCLIStrategy);
            } else {
                cli.setStrategy(appCLIStrategy);
            }
            runCLI = cli.execute();
        }
    }
}