package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class NavigationHelper {
    public void openGroupPage() {
        $("a[href='group.php']").click();
    }

    public void openHomePage() {
        $("a[href='./']").click();
    }

    public void acceptAlert() {
        switchTo().alert().accept();
    }
}
