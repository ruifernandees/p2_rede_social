package presentation.controllers;

import domain.dtos.ShowFeedDTO;
import domain.entities.FeedContent;
import domain.entities.Message;
import domain.entities.User;
import domain.repositories.implementations.MemoryMessagesRepository;
import domain.repositories.implementations.MemoryUsersRepository;
import domain.singletons.AuthenticationProvider;
import domain.usecases.ShowFeedUseCase;

public class ShowFeedController {
    public static void execute() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        ShowFeedUseCase showFeedUseCase = new ShowFeedUseCase(
            new MemoryUsersRepository(), 
            new MemoryMessagesRepository() 
        );
        User currentUser = authenticationProvider.getCurrentUser();
        Integer currentUserIndex = authenticationProvider.getCurrentUserIndex();
        FeedContent restrictedFeed = showFeedUseCase.execute(new ShowFeedDTO(currentUser, currentUserIndex));
        System.out.println("=== Feed ===");
        for (Message message : restrictedFeed.messages) {
            System.out.println(message);
        }
    }
}
