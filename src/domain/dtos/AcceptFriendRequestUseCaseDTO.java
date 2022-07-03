package domain.dtos;

import domain.entities.User;

public class AcceptFriendRequestUseCaseDTO {
    public User user;
    public Integer userIndex;

    public AcceptFriendRequestUseCaseDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    } 
}
