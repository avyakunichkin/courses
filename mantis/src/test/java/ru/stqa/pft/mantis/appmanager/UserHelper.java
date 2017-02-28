package ru.stqa.pft.mantis.appmanager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class UserHelper extends HelperBase{

    public UserHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String login, String password) {
        open(app.getProperty("web.baseUrl"));
        $("input[name='username']").setValue(login);
        $("input[name='password']").setValue(password);
        $("input[type='submit']").click();
    }

    public void registration(String username, String email) {
        open(app.getProperty("web.baseUrl") + "/signup_page.php");
        fill("[name='username']", username);
        fill("[name='email']", email);
        $("input[value='Signup']").click();
    }

    public void toLinkConfirm(String confirmationLink, String password) {
        open(confirmationLink);
        fill("[name=password]", password);
        fill("[name=password_confirm]", password);
        $("input[value='Update User']").click();
    }
}
