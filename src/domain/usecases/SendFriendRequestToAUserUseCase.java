package domain.usecases;

import domain.dtos.SendFriendRequestToAUserUseCaseDTO;
import domain.entities.User;
import domain.exceptions.FriendshipException;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class SendFriendRequestToAUserUseCase {
    
    private IUsersRepository repository;

    public SendFriendRequestToAUserUseCase(IUsersRepository repository) {
        this.repository = repository;
    } 

    public void execute(SendFriendRequestToAUserUseCaseDTO params) throws FriendshipException {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        Integer currentLoggedUserIndex= authenticationProvider.getCurrentUserIndex();
        if (currentLoggedUser.requestingYourFriendshipIndexes.contains(params.userIndex)) {
            currentLoggedUser.friendsIndexes.add(params.userIndex);
            params.user.friendsIndexes.add(currentLoggedUserIndex);
            Integer indexC = params.userIndex;
            currentLoggedUser.requestingYourFriendshipIndexes.removeIf(index -> index == indexC);
            this.repository.update(params.user);
            this.repository.update(currentLoggedUser);
            throw new FriendshipException(params.user.username + " adicionado, pois ele já havia requisitado a você!");
        }
        params.user.requestingYourFriendshipIndexes.add(currentLoggedUserIndex);
        this.repository.update(params.user);
    }
}
