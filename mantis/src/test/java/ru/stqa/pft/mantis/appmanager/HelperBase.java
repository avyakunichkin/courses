package ru.stqa.pft.mantis.appmanager;

import static com.codeborne.selenide.Selenide.$;

public class HelperBase {

    protected ApplicationManager app;

    public HelperBase(ApplicationManager app){
        this.app = app;
    }

    protected void fill(String cssSelector, String text) {
        if(text != null) {
            if (!$(cssSelector).getValue().equals(text)) {
                $(cssSelector).setValue(text);
            }
        }
    }
}
