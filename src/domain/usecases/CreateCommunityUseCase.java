package domain.usecases;

import java.util.ArrayList;

import domain.dtos.FindCommunityByNameDTO;
import domain.entities.Community;
import domain.exceptions.CommunityException;
import domain.repositories.ICommunitiesRepository;
import domain.singletons.AuthenticationProvider;

public class CreateCommunityUseCase {
    private ICommunitiesRepository repository;

    public CreateCommunityUseCase(ICommunitiesRepository repository) {
        this.repository = repository;
    }  

    public void execute(String communityName, String description) throws CommunityException {
        FindCommunityByNameDTO communityByNameDTO = this.repository.findByName(communityName);
        if (communityByNameDTO != null) {
            throw new CommunityException("Já existe uma comunidade com esse nome!");
        }
        AuthenticationProvider authenticationProvider = AuthenticationProvider.getInstance();
        String ownerUsername = authenticationProvider.getCurrentUser().username;
        this.repository.create(new Community(ownerUsername, communityName, description));
    }
}
