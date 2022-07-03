package domain.dtos;

import domain.entities.User;

public class SendFriendRequestToAUserUseCaseDTO {
    public User user;
    public Integer userIndex;

    public SendFriendRequestToAUserUseCaseDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    } 
}
