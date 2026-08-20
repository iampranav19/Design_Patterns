package proxy.good;

public class Main {

    public static void main(String[] args) {
        User alice = new User(true);
        User bob = new User(false);

        VideoDownloader downloader1 = new VideoDownloaderProxy(alice);
        System.out.println(downloader1.downloadVideo("https://video.com/proxy-pattern"));

        // A second proxy, a different "session" for the same request — the
        // cache hit is checked before the real downloader is ever created,
        // so "Connecting to CDN..." only ever prints once, total.
        VideoDownloader downloader2 = new VideoDownloaderProxy(alice);
        System.out.println(downloader2.downloadVideo("https://video.com/proxy-pattern"));

        try {
            new VideoDownloaderProxy(bob).downloadVideo("https://video.com/movie");
        } catch (SecurityException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
        try {
            downloader1.downloadVideo("https://video.com/region-locked-movie");
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}