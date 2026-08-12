package singleton.bad;

public class Main {

    public static void main(String[] args) {
        DatabaseConnectionManager pool1 = new DatabaseConnectionManager();
        DatabaseConnectionManager pool2 = new DatabaseConnectionManager();

        pool1.connect();
        pool1.connect();
        pool2.connect();

        System.out.println("pool1 active connections: " + pool1.getActiveConnections());
        System.out.println("pool2 active connections: " + pool2.getActiveConnections());
        System.out.println("Same instance? " + (pool1 == pool2));
        // Two separate connection pools were created — wasted resources and
        // inconsistent state, when the app should be sharing ONE pool.
    }
}