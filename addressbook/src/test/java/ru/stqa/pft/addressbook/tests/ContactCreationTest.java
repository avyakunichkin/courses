package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        List<ContactData> before = app.getContactHelper().getContactList();
        ContactData contact = new ContactData("testFirstName", "testLastName", "testAddress", "testEmail", "testHomePhone");
        app.getContactHelper().createContact(contact);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        int max = 0;
        for (ContactData c : after){
            if (max < c.getId()){
                max = c.getId();
            }
        }
        contact.setId(max);
        before.add(contact);
        Assert.assertEquals(new HashSet(before), new HashSet(after));
    }
}
