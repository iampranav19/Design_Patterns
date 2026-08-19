package proxy.bad;

// BAD: "Connecting to CDN..." runs the instant each RealVideoDownloader is
// constructed — there's no way to defer that cost until it's actually
// needed, because creation and use aren't separated by anything.
public class Main {

    public static void main(String[] args) {
        User alice = new User(true);
        User bob = new User(false);

        RealVideoDownloader downloader1 = new RealVideoDownloader();
        System.out.println(downloader1.downloadVideo("https://video.com/proxy-pattern", alice));

        // A fresh downloader for what's meant to be the same request — pays
        // the CDN cost again, and its own empty cache can't help either.
        RealVideoDownloader downloader2 = new RealVideoDownloader();
        System.out.println(downloader2.downloadVideo("https://video.com/proxy-pattern", alice));

        try {
            downloader1.downloadVideo("https://video.com/movie", bob);
        } catch (SecurityException e) {
            System.out.println("Blocked: " + e.getMessage());
        }

        try {
            downloader1.downloadVideo("https://video.com/region-locked-movie", alice);
        } catch (IllegalArgumentException e) {
            System.out.println("Blocked: " + e.getMessage());
        }
    }
}