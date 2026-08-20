package bridge.good;

public class SmartTVPlayer extends Player {

    public SmartTVPlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {
        System.out.println("Smart TV: Playing " + title + " in " + quality.describe());
    }
}