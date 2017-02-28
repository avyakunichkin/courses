package ru.stqa.pft.mantis.appmanager;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AdminHelper extends HelperBase{

    public AdminHelper(ApplicationManager app) {
        super(app);
    }
    private SelenideElement resetButton = $(".button[value='Reset Password']");

    public AdminHelper openUserById(int id) {
        open(String.format(app.getProperty("web.baseUrl") + "manage_user_edit_page.php?user_id=%s", id));
        resetButton.shouldBe(visible);
        return this;
    }

    public AdminHelper resetPassword() {
        resetButton.click();
        return this;
    }
}
