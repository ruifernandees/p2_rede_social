package domain.repositories;

import java.util.ArrayList;

import domain.entities.Community;
import domain.entities.User;

public interface ICommunitiesRepository {
    public ArrayList<Community> findCommunitiesByUsername(String username);
    public void create(User user);
    public void update(User user);
}
