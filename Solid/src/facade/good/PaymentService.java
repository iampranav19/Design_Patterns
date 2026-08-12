package facade.good;

public class PaymentService {

    public boolean charge(String customer, double amount) {
        System.out.println("Charging " + customer + " $" + amount);
        return true;
    }
}