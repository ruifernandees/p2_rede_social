package domain.repositories;

import java.util.ArrayList;

import domain.dtos.FindCommunityByNameDTO;
import domain.entities.Community;
import domain.entities.Message;

public interface ICommunitiesRepository {
    public ArrayList<Community> findCommunitiesByUsername(String username);
    public ArrayList<Community> findAll();
    public FindCommunityByNameDTO findByName(String name);
    public void create(Community community);
    public void addMessageInCommunity(Message message, Integer communityIndex);
    public void update(Community community);
    public void removeUserAndCommunities(String username);
}
