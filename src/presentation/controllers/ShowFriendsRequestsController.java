package presentation.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.dtos.AcceptFriendRequestUseCaseDTO;
import domain.entities.User;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.AcceptFriendRequestUseCase;
import domain.usecases.FindUserByIndexUseCase;

public class ShowFriendsRequestsController {
    public static void execute(Scanner reader)  {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        System.out.println(currentLoggedUser.requestingYourFriendshipIndexes.size());
        int amountOfRequests = currentLoggedUser.requestingYourFriendshipIndexes.size();
        if (amountOfRequests == 0) {
            System.out.println("\nNão há solicitações de amizade no momento!\n");
            return;
        }
        ArrayList<Integer> addedUsersIndexes = new ArrayList<Integer>();
        MemoryUsersRepository memoryUsersRepository = new MemoryUsersRepository();
        FindUserByIndexUseCase findUserByIndexUseCase = new FindUserByIndexUseCase(memoryUsersRepository);
        Integer counter = 0;
        for (Integer currentUserLoopIndex : currentLoggedUser.requestingYourFriendshipIndexes){
            User currentUser = findUserByIndexUseCase.execute(currentUserLoopIndex);
            System.out.println(currentUser);
            System.out.println(counter + 1 + ". " + currentUser.username + ". Aceitar? [S/N]");
            String response = reader.next();
            if (response.equals("S")) {
                AcceptFriendRequestUseCase acceptFriendRequestUseCase = new AcceptFriendRequestUseCase(memoryUsersRepository);
                acceptFriendRequestUseCase.execute(new AcceptFriendRequestUseCaseDTO(currentUser, currentUserLoopIndex));
                System.out.println(currentUser.username + " adicionado!");
                addedUsersIndexes.add(counter);
                counter++;
            } else if (response.equals("N")) {
                System.out.println(currentUser.username + " não adicionado!");
            } else {
                System.out.println("Resposta inválida!");
                return;
            }
        }
        currentLoggedUser = authenticationProvider.getCurrentUser();
        for (Integer integer : addedUsersIndexes) {
            currentLoggedUser.requestingYourFriendshipIndexes.remove(integer);
            memoryUsersRepository.update(currentLoggedUser);
        }
        System.out.println(currentLoggedUser.requestingYourFriendshipIndexes);
    }
}
