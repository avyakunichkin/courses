package ru.stqa.pft.addressbook.appmanager;

import static com.codeborne.selenide.Selenide.$;

public class BaseHelper {

    protected void fill(String cssSelector, String text) {
        if(text != null) {
            if (!$(cssSelector).getValue().equals(text)) {
                $(cssSelector).setValue(text);
            }
        }
    }
}
