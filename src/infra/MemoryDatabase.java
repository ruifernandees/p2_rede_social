package infra;

import java.util.ArrayList;

import domain.entities.Community;
import domain.entities.User;

public class MemoryDatabase {
    public ArrayList<User> users; 
    public ArrayList<Community> communities; 

    public MemoryDatabase() {
        this.users = new ArrayList<User>();
        this.communities = new ArrayList<Community>();
        this.addDefaultUsers();
        this.addDefaultCommunities();
    }

    private void addDefaultUsers() {
        this.users.add(new User("João", "joao", "joao123", new ArrayList<Integer>(), new ArrayList<Integer>()));
        this.users.add(new User("Maria", "maria", "maria123", new ArrayList<Integer>(), new ArrayList<Integer>()));
        this.users.add(new User("José", "jose", "jose123", new ArrayList<Integer>(), new ArrayList<Integer>()));
    }

    private void addDefaultCommunities() {
        this.communities.add(new Community("José", "Comunidade do bairro", "Uma descrição"));
        this.communities.add(new Community("João", "Os gamers", "Outra descrição"));
    }
}
