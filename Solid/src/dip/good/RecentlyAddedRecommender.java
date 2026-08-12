package dip.good;

import java.util.List;

public class RecentlyAddedRecommender implements RecommendationSource {

    @Override
    public List<String> getRecommendations() {
        return List.of("New Movie A", "New Series B", "New Documentary C");
    }
}