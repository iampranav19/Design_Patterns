package prototype.good;

import java.util.HashMap;
import java.util.Map;

public class EmailTemplateRegistry {

    private static final Map<String, EmailTemplate> templates = new HashMap<>();

    static{
        templates.put("welcome", new WelcomeEmail());
    }

    public static EmailTemplate getTemplate(String templateName) {
        EmailTemplate template = templates.get(templateName);
        if (template == null) {
            throw new IllegalArgumentException("No template registered for name: " + templateName);
        }
        return template.clone();
    }


}
