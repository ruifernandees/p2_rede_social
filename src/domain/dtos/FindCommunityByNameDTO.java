package domain.dtos;

import domain.entities.Community;

public class FindCommunityByNameDTO {
    public Community community;
    public Integer communityIndex;

    public FindCommunityByNameDTO(Community community, Integer communityIndex) {
        this.community = community;
        this.communityIndex = communityIndex;
    }
}
