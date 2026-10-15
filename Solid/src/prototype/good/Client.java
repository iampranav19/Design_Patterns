package prototype.good;

public class Client {

    public static void main(String[] args) {

        EmailTemplate welcomeEmail= EmailTemplateRegistry.getTemplate("welcome");
        welcomeEmail.setContent("Welcome to our website!");
        welcomeEmail.send();
        System.out.println(welcomeEmail);

        EmailTemplate thankYouEmail= EmailTemplateRegistry.getTemplate("welcome");
        thankYouEmail.setContent("Thank you for your order!");
        thankYouEmail.send();
        System.out.println(thankYouEmail);

    }
}
