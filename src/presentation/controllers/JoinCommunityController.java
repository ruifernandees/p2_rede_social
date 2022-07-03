package presentation.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.entities.Community;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.FindAllCommunitiesUseCase;
import domain.usecases.JoinCommunityUseCase;

public class JoinCommunityController {
    public static void execute(Scanner reader) {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        String username = authenticationProvider.getCurrentUser().username;
        JoinCommunityController.showAllCommunities();
        System.out.println("Informe o número da comunidade: ");
        Integer number = reader.nextInt() - 1;
        try {
            JoinCommunityUseCase joinCommunityUseCase = new JoinCommunityUseCase(
                new MemoryCommunitiesRepository()
            );
            try {
                Community community = joinCommunityUseCase.execute(number, username);
                System.out.println("Você entrou na comunidade " + community.name + ".");    
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Comunidade inválida!");
        }
    }

    private static void showAllCommunities() {
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
    }
}
