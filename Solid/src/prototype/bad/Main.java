package prototype.bad;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        // Sending a welcome email to 3 new signups — each one rebuilds the
        // entire branded template from scratch just to swap in a name.
        String[] recipients = {"Alice", "Bob", "Charlie"};
        for (String recipient : recipients) {
            EmailTemplate email = new EmailTemplate("Welcome to ACME!", recipient);
            System.out.println("Sent: " + email);
        }

        System.out.println("Total time: " + (System.currentTimeMillis() - start) + "ms");
    }
}