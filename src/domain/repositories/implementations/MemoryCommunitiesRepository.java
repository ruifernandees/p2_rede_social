package domain.repositories.implementations;

import java.util.ArrayList;

import domain.entities.Community;
import domain.entities.User;
import domain.repositories.ICommunitiesRepository;
import infra.singletons.DatabaseConnection;

public class MemoryCommunitiesRepository implements ICommunitiesRepository {

    DatabaseConnection connection;

    public MemoryCommunitiesRepository() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public ArrayList<Community> findCommunitiesByUsername(String username) {
        ArrayList<Community> allCommunities = this.connection.getMemoryDatabase().communities;
        ArrayList<Community> userCommunities = new ArrayList<Community>();
        for (Community community : allCommunities) {
            if (community.users.contains(username)) {
                userCommunities.add(community);
            }
        }
        return userCommunities;
    }

    @Override
    public void create(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update(User user) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeUserAndCommunities(String username) {
        ArrayList<Community> allCommunities = this.connection.getMemoryDatabase().communities; 
        for (Community community : allCommunities) {
            community.users.removeIf(user -> user.equals(username));
            if (community.ownerUsername.equals(username)) {
                allCommunities.remove(community);
            }
        }
        this.connection.getMemoryDatabase().communities = allCommunities;
    }

    @Override
    public ArrayList<Community> findAll() {
        return this.connection.getMemoryDatabase().communities;
    }
  
}
