package dip.good;

import java.util.List;

// The abstraction both the high-level engine and every low-level strategy depend on.
public interface RecommendationSource {
    List<String> getRecommendations();
}