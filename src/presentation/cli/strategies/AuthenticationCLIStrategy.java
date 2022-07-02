package presentation.cli.strategies;

import entities.User;
import presentation.cli.CLIConstants;
import presentation.cli.ICLIStrategy;
import singletons.AuthenticationProvider;
import singletons.DatabaseConnection;

public class AuthenticationCLIStrategy implements ICLIStrategy {
    @Override
    public boolean execute() {
        User currentUser = DatabaseConnection.getConnection().getMemoryDatabase().users.get(0);
        AuthenticationProvider.getInstance().setCurrentUser(currentUser);
        System.out.println("Authentication");
        return CLIConstants.RUN_CLI; 
    }
}
