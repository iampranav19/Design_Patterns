package proxy.good;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// GOOD: implements the same interface as RealVideoDownloader and stands in
// front of it. Access checks, filtering, and the cache lookup all happen
// here — the real downloader is only ever constructed (and only ever
// touched) once all of that has passed and nothing was cached, so its
// expensive setup is deferred until it's genuinely needed.
public class VideoDownloaderProxy implements VideoDownloader {

    private static final Set<String> RESTRICTED_URLS = Set.of("https://video.com/region-locked-movie");
    private static final Map<String, String> cache = new HashMap<>();

    private final User user;
    private RealVideoDownloader realDownloader;

    public VideoDownloaderProxy(User user) {
        this.user = user;
    }

    @Override
    public String downloadVideo(String videoUrl) {
        if (!user.isPremium()) {
            throw new SecurityException("Access denied");
        }
        if (RESTRICTED_URLS.contains(videoUrl)) {
            throw new IllegalArgumentException("Content blocked");
        }
        if (cache.containsKey(videoUrl)) {
            System.out.println("Serving from cache");
            return cache.get(videoUrl);
        }

        if (realDownloader == null) {
            realDownloader = new RealVideoDownloader();
        }
        String content = realDownloader.downloadVideo(videoUrl);
        cache.put(videoUrl, content);
        return content;
    }
}