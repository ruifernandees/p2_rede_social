package presentation.cli.strategies;

import presentation.cli.CLIConstants;
import presentation.cli.ICLIStrategy;

public class AuthenticationCLIStrategy implements ICLIStrategy {
    @Override
    public boolean execute() {
        System.out.println("Authentication");
        return CLIConstants.CLOSE_CLI; 
    }
}
