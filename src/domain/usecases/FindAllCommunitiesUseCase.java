package domain.usecases;

import java.util.ArrayList;

import domain.entities.Community;
import domain.repositories.ICommunitiesRepository;

public class FindAllCommunitiesUseCase {
    private ICommunitiesRepository repository;

    public FindAllCommunitiesUseCase(ICommunitiesRepository repository) {
        this.repository = repository;
    }  

    public ArrayList<Community> execute() {
        return this.repository.findAll();
    }
}
