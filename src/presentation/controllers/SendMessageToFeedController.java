package presentation.controllers;

import java.util.Scanner;

import domain.entities.User;
import domain.repositories.implementations.MemoryMessagesRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.SendMessageToFeedUseCase;

public class SendMessageToFeedController {
    public static void execute(Scanner reader ) {
        reader.nextLine();
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        System.out.println("Nova mensagem: ");
        String message = reader.nextLine();
        try {
            SendMessageToFeedUseCase sendMessageToFeedUseCase = new SendMessageToFeedUseCase(new MemoryMessagesRepository()); 
            sendMessageToFeedUseCase.execute(message, currentUser.username);
            System.out.println(message);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } 

    }
}
