package domain.repositories;

import domain.dtos.FindByLoginDTO;

public interface IUsersRepository {
    public FindByLoginDTO findByLogin(String login);
}
