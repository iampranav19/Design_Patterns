package proxy.bad;

import java.util.HashMap;
import java.util.Map;

// BAD: downloading is RealVideoDownloader's one job, but it's also doing
// access control, region filtering, and caching — 4 responsibilities in one
// class. Any other downloader (RealMusicDownloader, RealDocumentDownloader)
// has to reimplement all of this from scratch, and nothing can get the raw,
// unrestricted downloader anymore — not even an internal QA tool.
//
// It doesn't even deliver on caching: the cache lives on the instance, so
// two separately-constructed downloaders for the "same" request never share
// a cache — see Main, where downloader2 redownloads a URL downloader1
// already fetched.
public class RealVideoDownloader {

    private final Map<String, String> cache = new HashMap<>();

    public RealVideoDownloader() {
        System.out.println("Connecting to CDN...");
    }

    public String downloadVideo(String videoUrl, User user) {
        if (!user.isPremium()) {
            throw new SecurityException("Access denied");
        }
        if (isRestricted(videoUrl)) {
            throw new IllegalArgumentException("Content blocked");
        }
        if (cache.containsKey(videoUrl)) {
            System.out.println("Serving from cache");
            return cache.get(videoUrl);
        }
        System.out.println("Downloading video from URL: " + videoUrl);
        String content = "Video content from " + videoUrl;
        cache.put(videoUrl, content);
        return content;
    }

    private boolean isRestricted(String videoUrl) {
        return videoUrl.contains("region-locked");
    }
}