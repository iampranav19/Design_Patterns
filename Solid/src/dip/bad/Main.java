package dip.bad;

public class Main {

    public static void main(String[] args) {
        RecommendationEngine engine = new RecommendationEngine();

        System.out.println("Home screen: " + engine.getHomeScreenRecommendations());

        // Want the home screen to show Genre-Based picks instead? Too bad —
        // RecommendationEngine.java must be opened and edited to change it.
    }
}