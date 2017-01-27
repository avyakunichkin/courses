package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTest extends TestBase{

    @Test
    public void testContactModification(){
        app.getContactHelper().initModificationContact();
        app.getContactHelper().fillContactForm(new ContactData("firstName1", "lastName1", "address1", "email1", "homePhone1"), false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().openHomePage();
    }
}
