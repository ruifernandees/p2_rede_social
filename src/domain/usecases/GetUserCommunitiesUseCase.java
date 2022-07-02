package domain.usecases;

import java.util.ArrayList;

import domain.entities.Community;
import domain.repositories.ICommunitiesRepository;

public class GetUserCommunitiesUseCase {
    private ICommunitiesRepository repository;

    public GetUserCommunitiesUseCase(ICommunitiesRepository repository) {
        this.repository = repository;
    }  

    public ArrayList<Community> execute(String username) {
        return this.repository.findCommunitiesByUsername(username);
    }
}
