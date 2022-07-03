package presentation.controllers;

import java.util.Scanner;

import domain.exceptions.CommunityException;
import domain.repositories.implementations.MemoryCommunitiesRepository;
import domain.usecases.CreateCommunityUseCase;

public class CreateCommunityController {
    public static void execute(Scanner reader) {
        reader.nextLine();
        System.out.println("Nome da comunidade: ");
        String name = reader.nextLine();
        System.out.println("Descrição da comunidade: ");
        String description = reader.nextLine();
        CreateCommunityUseCase createCommunityUseCase = new CreateCommunityUseCase(
            new MemoryCommunitiesRepository()
        );
        try {
            createCommunityUseCase.execute(name, description);
            System.out.println("Comunidade criada com sucesso!");
        } catch (CommunityException e) {
            System.out.println(e.getMessage());
        }
    }
}
