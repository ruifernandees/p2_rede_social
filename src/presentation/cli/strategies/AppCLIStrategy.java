package presentation.cli.strategies;

import presentation.cli.CLIConstants;
import presentation.cli.ICLIStrategy;

public class AppCLIStrategy implements ICLIStrategy {
    @Override
    public boolean execute() {
        System.out.println("App");
        return CLIConstants.CLOSE_CLI; 
    }
}
