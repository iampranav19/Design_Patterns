package dip.good;

import java.util.List;

public class TrendingNowRecommender implements RecommendationSource {

    @Override
    public List<String> getRecommendations() {
        return List.of("Trending Show X", "Trending Movie Y", "Trending Special Z");
    }
}