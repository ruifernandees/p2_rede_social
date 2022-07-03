package presentation.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.entities.Community;
import domain.entities.Message;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindAllCommunitiesUseCase;
import domain.usecases.SendMessageToCommunityUseCase;

public class AccessCommunitiesController {
    public static void execute(Scanner reader) {
        System.out.println("==== COMUNIDADES ====");
        MemoryCommunitiesRepository  memoryCommunitiesRepository = new MemoryCommunitiesRepository();
        FindAllCommunitiesUseCase findAllCommunitiesUseCase = new FindAllCommunitiesUseCase(
            memoryCommunitiesRepository 
        );
        ArrayList<Community> allCommunities = findAllCommunitiesUseCase.execute();
        Integer counter = 1;
        for (Community community : allCommunities) {
            System.out.println("[" + counter + "]" + community.name);
            System.out.println("  => " + community.description);
            counter++;
        }
        System.out.println("Informe o número da comunidade: ");
        Integer number = reader.nextInt() - 1;
        try {
            Community chosenCommunity = allCommunities.get(number);
            AccessCommunitiesController.showCommunity(chosenCommunity); 
            reader.nextLine();
            System.out.println("Nova mensagem: [N, para sair] ");
            String message = reader.nextLine();
            if (message.equals("N")) {
                return;
            }
            System.out.println(message);
            AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
            String currentUsername = authenticationProvider.getCurrentUser().username;
            SendMessageToCommunityUseCase sendMessageToCommunityUseCase = new SendMessageToCommunityUseCase(
                memoryCommunitiesRepository
            );
            sendMessageToCommunityUseCase.execute(
                new Message(message, currentUsername), 
                number
            );
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Comunidade inválida!");
            reader.nextLine();
        }
    }

    private static void showCommunity(Community community) {
        System.out.println("\n=== " + community.name + " ===");
        System.out.println("Descrição: " + community.description);
        System.out.println("Dono: " + community.ownerUsername);
        System.out.println("Membros: ");
        for (int i = 0; i < community.users.size(); i++) {
            System.out.println(community.users.get(i));
        }
        System.out.println("\n=== Conteúdo ===");
        community.content.show();
    }
}
