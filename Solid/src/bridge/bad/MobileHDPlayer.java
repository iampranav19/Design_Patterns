package bridge.bad;

public class MobileHDPlayer implements PlayQuality {
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in HD");
    }
}