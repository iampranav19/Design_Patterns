package bridge.bad;

// BAD: Platform (Web/Mobile/SmartTV) and Quality (HD/UltraHD/4K) are two
// independent things that vary, but inheritance forces one class per
// combination. 3 platforms x 3 qualities = up to 9 classes, and a 4th
// platform or a 4th quality multiplies the other dimension all over again.
//
// Mobile + 4K is a real combination nobody's written yet — even though the
// "Mobile" logic already exists (MobileHDPlayer) and the "4K" logic already
// exists (Web4KPlayer), neither can be reused here; a brand new
// Mobile4KPlayer class would have to duplicate one of them from scratch.
public class Main {

    public static void main(String[] args) {
        PlayQuality webHD = new WebHDPlayer();
        webHD.play("Interstellar");

        PlayQuality mobileHD = new MobileHDPlayer();
        mobileHD.play("Interstellar");

        PlayQuality smartTvUltraHD = new SmartTVUltraHDPlayer();
        smartTvUltraHD.play("Interstellar");

        PlayQuality web4K = new Web4KPlayer();
        web4K.play("Interstellar");
    }
}