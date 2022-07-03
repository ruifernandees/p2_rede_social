package domain.usecases;

import java.util.ArrayList;

import domain.entities.Community;
import domain.entities.User;
import domain.repositories.ICommunitiesRepository;
import domain.repositories.IMessagesRepository;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;
import infra.singletons.DatabaseConnection;

public class RemoveUserUseCase {
    private IUsersRepository usersRepository;
    private ICommunitiesRepository communitiesRepository;

    public RemoveUserUseCase(
        IUsersRepository usersRepository,
        ICommunitiesRepository communitiesRepository
    ) {
        this.usersRepository = usersRepository;
        this.communitiesRepository = communitiesRepository;
    }      

    public void execute() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        Integer currentLoggedUserIndex= authenticationProvider.getCurrentUserIndex();
        String username = currentLoggedUser.username;
        this.communitiesRepository.removeUserAndCommunities(username);
        for (User user : this.usersRepository.findAll()) {
            user.friendsIndexes.removeIf(friend -> friend.equals(currentLoggedUserIndex));
            user.requestingYourFriendshipIndexes.removeIf(friendRequest -> friendRequest.equals(currentLoggedUserIndex));
            user.inbox.conversations.removeIf(conversation -> conversation.getFirstUser().equals(username) || conversation.getSecondUser().equals(username));
        }
        currentLoggedUser.isActivated = false;
        this.usersRepository.update(currentLoggedUser);
        authenticationProvider.setCurrentUser(null);
        authenticationProvider.setCurrentUserIndex(-1);
        // TTT
        DatabaseConnection.getConnection().getMemoryDatabase().feed.messages.removeIf(message -> message.username.equals(username));
    }
}
