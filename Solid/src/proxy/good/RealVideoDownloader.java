package proxy.good;

// The real subject: downloading is its only job. No access checks, no
// filtering, no caching — an internal QA tool can still construct this
// directly and get the raw, unrestricted downloader.
public class RealVideoDownloader implements VideoDownloader {

    public RealVideoDownloader() {
        System.out.println("Connecting to CDN...");
    }

    @Override
    public String downloadVideo(String videoUrl) {
        System.out.println("Downloading video from URL: " + videoUrl);
        return "Video content from " + videoUrl;
    }
}