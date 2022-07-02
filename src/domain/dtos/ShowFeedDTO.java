package domain.dtos;

import domain.entities.User;

public class ShowFeedDTO {
    public User user;
    public Integer userIndex;

    public ShowFeedDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    } 
}
