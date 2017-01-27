package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion(){
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData("testFirstName", "testLastName", "testAddress", "testEmail", "testHomePhone"));
        }
        app.getContactHelper().selectedContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().openHomePage();
    }
}
