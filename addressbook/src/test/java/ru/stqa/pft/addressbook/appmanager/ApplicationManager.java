package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationManager {

    private ContactHelper contactHelper = new ContactHelper();
    private GroupHelper groupHelper = new GroupHelper();
    private NavigationHelper navigationHelper = new NavigationHelper();
    private SessionHelper sessionHelper = new SessionHelper();

    public void init() {
        open("http://127.0.0.1:8080/addressbook/");
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        close();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }
}
