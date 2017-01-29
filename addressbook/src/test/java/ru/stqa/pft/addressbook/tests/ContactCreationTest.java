package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTest extends TestBase{

    @Test
    public void testContactCreation(){
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withAddress("testAddress")
                .withEmail("testEmail")
                .withPhone("testHomePhone");
        app.contact().create(contact);
        Contacts after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
