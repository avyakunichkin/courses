package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.ContactData;

import static com.codeborne.selenide.Selenide.$;

public class ContactHelper {
    public void submitCreationContact() {
        $("input[type='submit']").click();
    }

    public void fillContactForm(ContactData contactData) {
        $("input[name='firstname']").setValue(contactData.getFirstName());
        $("input[name='lastname']").setValue(contactData.getLastName());
        $("textarea[name='address']").setValue(contactData.getAddress());
        $("input[name='email']").setValue(contactData.getEmail());
        $("input[name='home']").setValue(contactData.getHomePhone());
    }

    public void initContactPage() {
        $("a[href='edit.php']").click();
    }
}
