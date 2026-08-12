package prototype.good;

public class Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        EmailTemplate prototype = new EmailTemplate("Welcome to ACME!"); // expensive build runs ONCE

        // Sending a welcome email to 3 new signups — each one is a cheap clone
        // of the prototype, just personalized with a name.
        String[] recipients = {"Alice", "Bob", "Charlie"};
        for (String recipient : recipients) {
            EmailTemplate email = prototype.clone();
            email.personalizeFor(recipient);
            System.out.println("Sent: " + email);
        }

        System.out.println("Total time: " + (System.currentTimeMillis() - start) + "ms");
    }
}