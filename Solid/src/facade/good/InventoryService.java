package facade.good;

public class InventoryService {

    public boolean checkStock(String item, int quantity) {
        System.out.println("Checking stock for " + quantity + "x " + item);
        return true;
    }

    public void reserveStock(String item, int quantity) {
        System.out.println("Reserving " + quantity + "x " + item);
    }
}