package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTest extends TestBase{

    @Test
    public void testContactDeletion(){
        app.getContactHelper().selectedContact();
        app.getContactHelper().deleteSelectedContacts();
        app.getNavigationHelper().acceptAlert();
        app.getNavigationHelper().openHomePage();
    }
}
