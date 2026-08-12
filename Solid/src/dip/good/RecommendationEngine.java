package dip.good;

import java.util.List;

// GOOD: The high-level module depends only on the RecommendationSource
// abstraction. It never changes, no matter which — or how many — strategies exist.
public class RecommendationEngine {

    private final RecommendationSource source;

    public RecommendationEngine(RecommendationSource source) {
        this.source = source;
    }

    public List<String> getHomeScreenRecommendations() {
        return source.getRecommendations();
    }
}