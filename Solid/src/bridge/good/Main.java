package bridge.good;

public class Main {

    public static void main(String[] args) {
        Player webHD = new WebPlayer(new HDQuality());
        webHD.play("Interstellar");

        Player mobileHD = new MobilePlayer(new HDQuality());
        mobileHD.play("Interstellar");

        Player smartTvUltraHD = new SmartTVPlayer(new UltraHDQuality());
        smartTvUltraHD.play("Interstellar");

        Player web4K = new WebPlayer(new FourKQuality());
        web4K.play("Interstellar");

        // Mobile + 4K — the combo bridge.bad couldn't reuse anything for.
        // Here it's zero new classes: an existing platform composed with an
        // existing quality.
        Player mobile4K = new MobilePlayer(new FourKQuality());
        mobile4K.play("Interstellar");
    }
}