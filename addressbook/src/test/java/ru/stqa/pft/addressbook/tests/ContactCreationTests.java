package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation(){
        Contacts before = app.contact().all();
        ContactData contact = new ContactData()
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withAddress("testAddress")
                .withEmail("testEmail")
                .withHomePhone("testHomePhone");
        app.contact().create(contact);
        Assert.assertEquals(app.contact().count(), before.size() + 1);
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
    }
}
