package domain.usecases;

import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class UpdateUserFeedMessageVisibleOption {
    private IUsersRepository repository;

    public UpdateUserFeedMessageVisibleOption(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(Boolean showOnlyForFriends) throws IllegalArgumentException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentUser = authenticationProvider.getCurrentUser();
        currentUser.feedMessageOnlyForFriends = showOnlyForFriends;
        authenticationProvider.setCurrentUser(currentUser);
        this.repository.update(currentUser);
    } 
}
