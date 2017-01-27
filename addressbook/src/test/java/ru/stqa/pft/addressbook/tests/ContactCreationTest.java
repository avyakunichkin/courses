package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        app.getContactHelper().initContactPage();
        app.getContactHelper().fillContactForm(new ContactData("testFirstName", "testLastName", "testAddress", "testEmail", "testHomePhone"), true);
        app.getContactHelper().submitCreationContact();
        app.getNavigationHelper().openHomePage();
    }

}
