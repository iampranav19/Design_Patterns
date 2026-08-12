package singleton.good;

// GOOD: The constructor is private, so the ONLY way to get a
// DatabaseConnectionManager is through getInstance() — guaranteeing every
// caller shares the exact same connection pool. `synchronized` keeps the
// lazy creation safe if two threads call getInstance() at the same time.
public class DatabaseConnectionManager {

    private static DatabaseConnectionManager instance;

    private int activeConnections = 0;

    private DatabaseConnectionManager() {
        System.out.println("Creating the one and only connection pool...");
    }

    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public void connect() {
        activeConnections++;
        System.out.println("Connection opened. Active connections: " + activeConnections);
    }

    public int getActiveConnections() {
        return activeConnections;
    }
}