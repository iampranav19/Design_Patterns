package bridge.good;

public class MobilePlayer extends Player {

    public MobilePlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {
        System.out.println("Mobile Player: Playing " + title + " in " + quality.describe());
    }
}