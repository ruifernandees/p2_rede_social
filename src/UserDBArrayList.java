import java.util.ArrayList;

import domain.entities.User;

public class UserDBArrayList implements IUserDB {
    public ArrayList<User> users;

    public UserDBArrayList() {
        this.users = new ArrayList<User>();
        this.users.add(new User("João", "joao", "joao123", new ArrayList<Integer>(), new ArrayList<Integer>()));
        this.users.add(new User("Maria", "maria", "maria123", new ArrayList<Integer>(), new ArrayList<Integer>()));
        this.users.add(new User("José", "jose", "jose123", new ArrayList<Integer>(), new ArrayList<Integer>()));
    }

    public void showAllUsers() {
        System.out.println("\n=== Usuários da rede ===");
        for (int i = 0; i < this.users.size(); i++) {
            System.out.println(this.users.get(i));
        }
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public User getUser(Integer id) {
        return this.users.get(id);
    }

    public Integer amountOfUsers() {
        return this.users.size();
    }

    public ArrayList<User> getAllUsers() {
        return this.users;
    }
}