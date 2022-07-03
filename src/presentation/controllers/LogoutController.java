package presentation.controllers;

import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.UpdateUserLoggedStatusUseCase;

public class LogoutController {
    public static void execute()  {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("Até mais, " + currentUser.username + "!");
        UpdateUserLoggedStatusUseCase updateUserLoggedStatusUseCase = new UpdateUserLoggedStatusUseCase(
            new MemoryUsersRepository()
        );
        updateUserLoggedStatusUseCase.execute(false);
    }
}
