package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.$;

public class SessionHelper {

    protected void login(String login, String password) {
        $("input[name='user']").setValue(login);
        $("input[name='pass']").setValue(password);
        $("input[type='submit']").click();
    }
}
