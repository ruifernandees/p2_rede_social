package domain.usecases;

import java.util.ArrayList;

import domain.entities.Community;
import domain.repositories.ICommunitiesRepository;

public class JoinCommunityUseCase {
    
    private ICommunitiesRepository repository;

    public JoinCommunityUseCase(ICommunitiesRepository repository) {
        this.repository = repository;
    }  


    public Community execute(Integer communityIndex, String username) throws Exception {
        ArrayList<Community> allCommunities = this.repository.findAll();
        Community chosenCommunity = allCommunities.get(communityIndex);
        if (chosenCommunity.users.contains(username)) {
            throw new Exception("Você já está nessa comunidade!");
        }
        chosenCommunity.addUser(username);
        this.repository.update(chosenCommunity);
        return chosenCommunity;
    }
}
