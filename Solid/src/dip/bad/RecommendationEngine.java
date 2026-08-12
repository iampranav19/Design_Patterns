package dip.bad;

import java.util.List;

// BAD: The high-level module (RecommendationEngine) reaches down and constructs
// a concrete low-level class itself. It is welded to TrendingNowRecommender —
// swapping in GenreBasedRecommender means opening and editing THIS class,
// and it can never be tested with a fake recommender.
public class RecommendationEngine {

    private final TrendingNowRecommender recommender = new TrendingNowRecommender();

    public List<String> getHomeScreenRecommendations() {
        return recommender.getRecommendations();
    }
}