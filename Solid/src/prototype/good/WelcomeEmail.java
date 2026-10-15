package prototype.good;

public class WelcomeEmail implements EmailTemplate{

    private String content;
    private String subject;

    WelcomeEmail(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public WelcomeEmail() {

    }


    @Override
    public EmailTemplate clone() {
        try {
            return (WelcomeEmail) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error cloning WelcomeEmail:",e);
        }
    }

    @Override
    public void setContent(String content) {
        this.content = content;

    }

    @Override
    public void send() {
        System.out.println("Sending welcome email to " + subject + " with content: " + content);
    }
}
