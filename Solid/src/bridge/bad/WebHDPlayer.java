package bridge.bad;

public class WebHDPlayer implements PlayQuality {
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in HD");
    }
}