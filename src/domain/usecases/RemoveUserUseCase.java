package domain.usecases;

import domain.entities.User;
import domain.repositories.ICommunitiesRepository;
import domain.repositories.IMessagesRepository;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class RemoveUserUseCase {
    private IUsersRepository usersRepository;
    private ICommunitiesRepository communitiesRepository;
    private IMessagesRepository messagesRepository;

    public RemoveUserUseCase(
        IUsersRepository usersRepository,
        ICommunitiesRepository communitiesRepository,
        IMessagesRepository messagesRepository
    ) {
        this.usersRepository = usersRepository;
        this.communitiesRepository = communitiesRepository;
        this.messagesRepository = messagesRepository;
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
        this.messagesRepository.removeAllMessagesFromUserByUsername(username);
    }
}
