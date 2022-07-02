package domain.repositories.implementations;

import java.util.ArrayList;

import domain.entities.Community;
import domain.entities.User;
import domain.repositories.ICommunitiesRepository;
import singletons.DatabaseConnection;

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
  
}
