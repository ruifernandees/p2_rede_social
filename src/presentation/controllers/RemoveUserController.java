package presentation.controllers;

import domain.entities.User;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.RemoveUserUseCase;

public class RemoveUserController {
    public static void execute() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        RemoveUserUseCase removeUserUseCase = new RemoveUserUseCase(
            new MemoryUsersRepository(),
            new MemoryCommunitiesRepository()
        );
        removeUserUseCase.execute();
        System.out.println("Sua conta foi removida do IFace, " + currentLoggedUser.username + ".");
    }
}
