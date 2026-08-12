package singleton.good;

public class Main {

    public static void main(String[] args) {
        DatabaseConnectionManager pool1 = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager pool2 = DatabaseConnectionManager.getInstance();

        pool1.connect();
        pool1.connect();
        pool2.connect();

        System.out.println("pool1 active connections: " + pool1.getActiveConnections());
        System.out.println("pool2 active connections: " + pool2.getActiveConnections());
        System.out.println("Same instance? " + (pool1 == pool2));
        // Only ONE connection pool was ever created — every caller shares the same state.
    }
}