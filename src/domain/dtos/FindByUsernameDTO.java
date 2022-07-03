package domain.dtos;

import domain.entities.User;

public class FindByUsernameDTO {
    public User user;
    public Integer userIndex;

    public FindByUsernameDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    }
}
