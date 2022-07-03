package presentation.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import domain.entities.Community;
import domain.entities.User;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.GetUserCommunitiesUseCase;

public class ViewProfileController {
    public static void execute(Scanner reader)     {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("\n" + currentUser + "\n");
        System.out.println("==== SUAS COMUNIDADES ====");
        GetUserCommunitiesUseCase getUserCommunitiesUseCase = new GetUserCommunitiesUseCase(
            new MemoryCommunitiesRepository()
        );
        ArrayList<Community> communities = getUserCommunitiesUseCase.execute(currentUser.username);
        for (Community community : communities) {
            System.out.println(community.name);
            System.out.println("  => " + community.description); 
        }
        System.out.println();
    }
}
