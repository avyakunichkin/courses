package ru.stqa.pft.mantis.appmanager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        open(app.getProperty("web.baseUrl") + "/signup_page.php");
        fill("[name='username']", username);
        fill("[name='email']", email);
        $("input[value='Signup']").click();
    }

    public void finish(String confirmationLink, String password) {
        open(confirmationLink);
        fill("[name=password]", password);
        fill("[name=password_confirm]", password);
        $("input[value='Update User']").click();
    }
}
