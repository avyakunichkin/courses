package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.$;

public class NavigationHelper {
    public void gotoGroupPage() {
        $("a[href='group.php']").click();
    }

    public void openHomePage() {
        $("a[href='./']").click();
    }
}
