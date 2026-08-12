package prototype.bad;

// BAD: Every new EmailTemplate re-does the full expensive template build —
// loading the HTML skeleton, injecting branding/header/footer, compiling
// inline styles — even though that work is identical for every email sent.
public class EmailTemplate {

    private final String subject;
    private final String recipientName;
    private final String body;

    public EmailTemplate(String subject, String recipientName) {
        this.subject = subject;
        this.recipientName = recipientName;
        this.body = buildExpensiveBody();
    }

    private String buildExpensiveBody() {
        System.out.println("Loading HTML skeleton from disk...");
        simulateExpensiveWork();
        System.out.println("Injecting branding, header, and footer...");
        simulateExpensiveWork();
        System.out.println("Compiling inline CSS styles...");
        simulateExpensiveWork();
        return "<html><body><header>ACME Corp</header><p>Hi " + recipientName
                + "</p><footer>Unsubscribe | ACME Inc.</footer></body></html>";
    }

    private void simulateExpensiveWork() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "Email to " + recipientName + " | Subject: " + subject + " | Body: " + body;
    }
}