package prototype.good;

// GOOD: The expensive template build (HTML skeleton, branding, CSS) happens
// ONCE to create a prototype. Every outgoing email is a cheap clone with just
// the recipient-specific fields swapped in.
public class EmailTemplate implements Cloneable {

    private String subject;
    private String recipientName;
    private final String bodyTemplate;

    public EmailTemplate(String subject) {
        this.subject = subject;
        this.bodyTemplate = buildExpensiveBody();
    }

    private String buildExpensiveBody() {
        System.out.println("Loading HTML skeleton from disk...");
        simulateExpensiveWork();
        System.out.println("Injecting branding, header, and footer...");
        simulateExpensiveWork();
        System.out.println("Compiling inline CSS styles...");
        simulateExpensiveWork();
        return "<html><body><header>ACME Corp</header><p>Hi {{name}}</p>"
                + "<footer>Unsubscribe | ACME Inc.</footer></body></html>";
    }

    private void simulateExpensiveWork() {
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void personalizeFor(String recipientName) {
        this.recipientName = recipientName;
    }

    @Override
    public EmailTemplate clone() {
        try {
            return (EmailTemplate) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e); // can't happen — this class implements Cloneable
        }
    }

    @Override
    public String toString() {
        String body = bodyTemplate.replace("{{name}}", recipientName);
        return "Email to " + recipientName + " | Subject: " + subject + " | Body: " + body;
    }
}