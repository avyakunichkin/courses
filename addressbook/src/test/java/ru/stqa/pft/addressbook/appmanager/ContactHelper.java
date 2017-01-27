package ru.stqa.pft.addressbook.appmanager;

import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;

public class ContactHelper extends BaseHelper{
    public void submitCreationContact() {
        $("input[name='submit']").click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        fill("input[name='firstname']", contactData.getFirstName());
        fill("input[name='lastname']", contactData.getLastName());
        fill("textarea[name='address']", contactData.getAddress());
        fill("input[name='email']", contactData.getEmail());
        fill("input[name='home']", contactData.getHomePhone());

        if($(byName("new_group")).exists()){
            $(byName("new_group")).selectOptionContainingText("testGroupName");
        } else {
            Assert.assertFalse($(byName("new_group")).isDisplayed());
        }
    }

    public void initContactPage() {
        $("a[href='edit.php']").click();
    }

    public void initModificationContact() {
        $("a[href*='edit.php?']").click();
    }

    public void updateContact() {
        $("input[name='update']").click();
    }

    public void deleteSelectedContacts() {
        $("input[value='Delete']").click();
    }

    public void selectedContact() {
        $("input[name='selected[]']").click();
    }
}
