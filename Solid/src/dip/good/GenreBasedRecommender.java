package dip.good;

import java.util.List;

public class GenreBasedRecommender implements RecommendationSource {

    @Override
    public List<String> getRecommendations() {
        return List.of("Sci-Fi Pick 1", "Sci-Fi Pick 2", "Sci-Fi Pick 3");
    }
}