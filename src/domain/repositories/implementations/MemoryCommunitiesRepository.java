package domain.repositories.implementations;

import java.util.ArrayList;

import domain.dtos.FindCommunityByNameDTO;
import domain.entities.Community;
import domain.entities.Message;
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
    public void create(Community community) {
        this.connection.getMemoryDatabase().communities.add(community);
    }

    @Override
    public void update(Community community) {
        FindCommunityByNameDTO communityByNameDTO = this.findByName(community.name);
        Integer index = communityByNameDTO.communityIndex;
        this.connection.getMemoryDatabase().communities.set(index, community);
    }

    @Override
    public FindCommunityByNameDTO findByName(String name) {
        ArrayList<Community> allCommunities = this.connection.getMemoryDatabase().communities;
        Integer counter = 0;
        for (Community community : allCommunities) {
            if (community.name.equals(name)) {
                return new FindCommunityByNameDTO(community, counter);
            }
            counter++;
        }
        return null;
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

    @Override
    public void addMessageInCommunity(Message message, Integer communityIndex) {
        this.findAll().get(communityIndex).content.addMessage(message);        
    }
  
}
