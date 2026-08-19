package proxy.good;

public class User {

    private final boolean premium;

    public User(boolean premium) {
        this.premium = premium;
    }

    public boolean isPremium() {
        return premium;
    }
}