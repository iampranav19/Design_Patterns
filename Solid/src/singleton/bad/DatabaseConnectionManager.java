package singleton.bad;

// BAD: Nothing stops the rest of the codebase from creating as many of these
// as it wants. Every "new" call spins up its own independent connection pool.
public class DatabaseConnectionManager {

    private int activeConnections = 0;

    public DatabaseConnectionManager() {
        System.out.println("Creating a new connection pool...");
    }

    public void connect() {
        activeConnections++;
        System.out.println("Connection opened. Active connections: " + activeConnections);
    }

    public int getActiveConnections() {
        return activeConnections;
    }
}