package factory.good;

public class Main {

    public static void main(String[] args) {
        Logistics logistics = LogisticsFactory.create("ROAD");
        logistics.send();

        Logistics seaLogistics = LogisticsFactory.create("SEA");
        seaLogistics.send();
        // The client only ever talks to Logistics and LogisticsFactory —
        // it never names a concrete transport class.
    }
}