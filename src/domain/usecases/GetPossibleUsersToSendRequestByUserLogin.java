package domain.usecases;

import java.util.ArrayList;

import domain.dtos.GetPossibleUsersToSendRequestByUserLoginDTO;
import domain.entities.User;
import domain.repositories.IUsersRepository;
import domain.singletons.AuthenticationProvider;

public class GetPossibleUsersToSendRequestByUserLogin {
    
    private IUsersRepository repository;

    public GetPossibleUsersToSendRequestByUserLogin(IUsersRepository repository) {
        this.repository = repository;
    } 

    public ArrayList<GetPossibleUsersToSendRequestByUserLoginDTO> execute() {
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        User currentLoggedUser = authenticationProvider.getCurrentUser();
        Integer currentLoggedUserIndex= authenticationProvider.getCurrentUserIndex();
        ArrayList<User> allUsers = this.repository.findAll();
        ArrayList<GetPossibleUsersToSendRequestByUserLoginDTO> usersRequestingYourFriendship = new ArrayList<GetPossibleUsersToSendRequestByUserLoginDTO>();
        for (int i = 0; i < allUsers.size(); i++) {
            User currentUser = allUsers.get(i);
            if (i == currentLoggedUserIndex) continue;
            if (currentLoggedUser.friendsIndexes.contains(i)) continue;
            if (!currentUser.isActivated) continue;
            if (currentUser.requestingYourFriendshipIndexes.contains(currentLoggedUserIndex)) continue;
            usersRequestingYourFriendship.add(
                new GetPossibleUsersToSendRequestByUserLoginDTO(currentUser, i)
            );

        }
        return usersRequestingYourFriendship;
    }
}
