package presentation.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.dtos.SendFriendRequestToAUserUseCaseDTO;
import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindAllUsersUseCase;
import domain.usecases.SendFriendRequestToAUserUseCase;

public class SendFriendRequestController {
    public static void execute(Scanner reader) {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        Integer currentLoggedUserIndex = authenticationProvider.getCurrentUserIndex();
        FindAllUsersUseCase findAllUsersUseCase = new FindAllUsersUseCase(new MemoryUsersRepository());
        ArrayList<User> allUsers = findAllUsersUseCase.execute();
        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);
            if (i == currentLoggedUserIndex) continue;
            if (currentLoggedUser.friendsIndexes.contains(i)) continue;
            if (!currentUser.isActivated) continue;
            if (currentUser.requestingYourFriendshipIndexes.contains(currentLoggedUserIndex)) continue;
            System.out.println(i + ". " + currentUser.username + ". Adicionar [S/N]");
            String response = reader.next();
            if (response.equals("S")) {
                SendFriendRequestToAUserUseCase sendFriendRequestToAUserUseCase = new SendFriendRequestToAUserUseCase(
                    new MemoryUsersRepository()
                );
                try {
                    sendFriendRequestToAUserUseCase.execute(new SendFriendRequestToAUserUseCaseDTO(currentUser, i));
                    System.out.println("Pedido feito a " + currentUser.username + " com sucesso!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else if (response.equals("N")) {
                continue;
            } else {
                System.out.println("Resposta inválida!");
            }
        }
    }
}
