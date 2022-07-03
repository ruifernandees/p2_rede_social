package domain.entities;
import java.util.ArrayList;

public class Community {
    public String name;
    public String description;
    public CommunityContent content;
    public ArrayList<String> users;
    public String ownerUsername;

    public Community(String ownerUsername, String name, String description) {
        this.content = new CommunityContent();
        this.users = new ArrayList<String>();
        this.users.add(ownerUsername);
        this.ownerUsername = ownerUsername;
        this.name = name;
        this.description = description;
    }

    public void addUser(String user) {
        this.users.add(user);
    }
}
