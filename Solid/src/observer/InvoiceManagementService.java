package observer;

public class InvoiceManagementService implements Subscriber<Order> {

    InvoiceManagementService()
    {
        Flipkart.registerSubscriber(Events.ORDER_PLACED, this);
        Flipkart.registerSubscriber(Events.CANCELLED, this);
    }
    @Override
    public void listen(Events  event, Order order) {
        if(event== Events.ORDER_PLACED)
        {
            generateInvoice();
        }
        else if(event == Events.CANCELLED)
        {
            cancelInvoice();
        }

    }

    public void generateInvoice() {
        System.out.println("InvoiceManagementService: Generating invoice");
    }

    public void cancelInvoice() {
        System.out.println("InvoiceManagementService: Cancelling invoice");
    }




}
