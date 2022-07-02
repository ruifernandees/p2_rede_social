package domain.repositories;

import domain.dtos.FindByLoginDTO;
import domain.entities.User;

public interface IUsersRepository {
    public FindByLoginDTO findByLogin(String login);
    public void create(User user);
    public void update(User user);
}
