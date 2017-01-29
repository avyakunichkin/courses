package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class NavigationHelper {
    public void groupPage() {
        if($("h1").exists()
                && $("h1").getText().equals("Groups")
                && $("input[name='new']").exists()){
            return;
        }
        $("a[href='group.php']").click();
    }

    public void homePage() {
        if($("#maintable").exists()){
            return;
        }
        $("a[href='./']").click();
    }

    public void acceptAlert() {
        switchTo().alert().accept();
    }
}
