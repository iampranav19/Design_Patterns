package bridge.bad;

public class SmartTVUltraHDPlayer implements PlayQuality {
    public void play(String title) {
        System.out.println("Smart TV: Playing " + title + " in ultra HD");
    }
}