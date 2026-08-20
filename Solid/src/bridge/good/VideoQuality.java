package bridge.good;

// The implementor side of the bridge: how a quality level describes itself.
// Varies independently of which platform is playing.
public interface VideoQuality {
    String describe();
}