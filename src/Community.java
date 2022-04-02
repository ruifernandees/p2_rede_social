import java.util.ArrayList;

public class Community {
    public String name;
    public String description;
    public Feed content;
    public ArrayList<User> users;
    public String ownerUsername;

    public Community(String ownerUsername, String name, String description) {
        this.content = new Feed();
        this.users = new ArrayList<User>();
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.description = description;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void show() {
        System.out.println("\n=== " + this.name + " ===");
        System.out.println("Descrição: " + this.description);
        System.out.println("Dono: " + this.ownerUsername);
        System.out.println("Membros: ");
        for (int i = 0; i < this.users.size(); i++) {
            System.out.println(this.users.get(i));
        }
        System.out.println("\n=== Conteúdo ===");
        this.content.show();
    }
}
