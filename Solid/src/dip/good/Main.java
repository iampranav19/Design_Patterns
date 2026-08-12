package dip.good;

public class Main {

    public static void main(String[] args) {
        RecommendationEngine recentlyAdded = new RecommendationEngine(new RecentlyAddedRecommender());
        RecommendationEngine trendingNow = new RecommendationEngine(new TrendingNowRecommender());
        RecommendationEngine genreBased = new RecommendationEngine(new GenreBasedRecommender());

        System.out.println("Recently Added: " + recentlyAdded.getHomeScreenRecommendations());
        System.out.println("Trending Now:   " + trendingNow.getHomeScreenRecommendations());
        System.out.println("Genre-Based:    " + genreBased.getHomeScreenRecommendations());

        // Adding a "Because You Watched" strategy? Just create a new RecommendationSource —
        // RecommendationEngine.java stays untouched.
    }
}