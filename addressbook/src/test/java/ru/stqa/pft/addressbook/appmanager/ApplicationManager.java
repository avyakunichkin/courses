package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationManager {

    String browser = "";

    private ContactHelper contactHelper = new ContactHelper();
    private GroupHelper groupHelper = new GroupHelper();
    private NavigationHelper navigationHelper = new NavigationHelper();
    private SessionHelper sessionHelper = new SessionHelper();

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() {
        Configuration.browser = browser;
        open("http://127.0.0.1:8080/addressbook/");
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        close();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }
}
