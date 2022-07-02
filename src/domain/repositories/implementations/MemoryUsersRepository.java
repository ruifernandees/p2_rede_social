package domain.repositories.implementations;

import java.util.ArrayList;

import domain.dtos.FindByLoginDTO;
import domain.entities.User;
import domain.repositories.IUsersRepository;
import singletons.DatabaseConnection;

public class MemoryUsersRepository implements IUsersRepository {

    DatabaseConnection connection;

    public MemoryUsersRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public FindByLoginDTO findByLogin(String login) {
        ArrayList<User> users = this.connection.getMemoryDatabase().users;
        for (int i = 0; i < users.size(); i++) {
            User currentUser = users.get(i);
            if (currentUser.login.equals(login)) {
                return new FindByLoginDTO(currentUser, i);
            }
        }
        return null;
    }
}
