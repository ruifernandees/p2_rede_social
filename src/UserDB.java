import java.util.ArrayList;

public class UserDB {
    public ArrayList<User> users;

    public UserDB() {
        this.users = new ArrayList<User>();
        this.users.add(new User("João", "joao", "joao123"));
        this.users.add(new User("Maria", "maria", "maria123"));
        this.users.add(new User("José", "jose", "jose123"));
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
}