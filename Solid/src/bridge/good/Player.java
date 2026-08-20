package bridge.good;

// GOOD: the abstraction side of the bridge. It holds a VideoQuality instead
// of hardcoding one — the two hierarchies (platform, quality) vary
// independently, and neither ever needs to know how many variants of the
// other exist.
public abstract class Player {

    protected final VideoQuality quality;

    protected Player(VideoQuality quality) {
        this.quality = quality;
    }

    public abstract void play(String title);
}