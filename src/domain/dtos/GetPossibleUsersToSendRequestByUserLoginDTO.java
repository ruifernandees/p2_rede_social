package domain.dtos;

import domain.entities.User;

public class GetPossibleUsersToSendRequestByUserLoginDTO {
    public User user;
    public Integer userIndex;

    public GetPossibleUsersToSendRequestByUserLoginDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    } 
}
