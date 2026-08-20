package bridge.good;

public class WebPlayer extends Player {

    public WebPlayer(VideoQuality quality) {
        super(quality);
    }

    @Override
    public void play(String title) {
        System.out.println("Web Player: Playing " + title + " in " + quality.describe());
    }
}