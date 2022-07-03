package presentation.controllers;

import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindUserByIndexUseCase;

public class ShowUserFriendsController {
    public static void execute()    {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        System.out.println("\n=== Amigos ===");
        FindUserByIndexUseCase findUserByIndexUseCase = new FindUserByIndexUseCase(new MemoryUsersRepository());
        for (int i = 0; i < currentLoggedUser.friendsIndexes.size(); i++) {
            Integer currentLoopIndex = currentLoggedUser.friendsIndexes.get(i);
            User friend = findUserByIndexUseCase.execute(currentLoopIndex);
            System.out.println(friend);
        }
    }
}
