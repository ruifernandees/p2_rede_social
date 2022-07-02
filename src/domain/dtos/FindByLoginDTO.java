package domain.dtos;

import domain.entities.User;

public class FindByLoginDTO {
    public User user;
    public Integer userIndex;

    public FindByLoginDTO(User user, Integer userIndex) {
        this.user = user;
        this.userIndex = userIndex;
    }
}
