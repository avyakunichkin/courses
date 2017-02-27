package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.Assert.assertTrue;

public class ContactDeleteFromGroupTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test1").withHeader("test2").withFooter("test3"));
        }
        if (app.db().contacts().size() == 0) {
            app.contact().create(new ContactData()
                    .withFirstName("testFirstName")
                    .withLastName("testLastName")
                    .withAddress("testAddress")
                    .withEmail("testEmail")
                    .withHomePhone("testHomePhone"));
        }
    }

    @Test
    public void testContactDeleteFromGroup(){
        app.goTo().homePage();
        Groups groups = app.db().groups();
        Contacts contacts = app.db().contacts();
        if (groups.stream().filter((g) -> g.getContacts().size()>0).count() == 0){
            app.contact().selectRandomContact(contacts).addContactToRandomGroup(groups);
        }
        GroupData group = app.db().groups().stream().filter((g) -> g.getContacts().size()>0).iterator().next();
        Contacts before = group.getContacts();
        ContactData contact = group.getContacts().iterator().next();
        app.contact().openGroup(group)
                .deleteContactFromGroup(contact.getId());

        Contacts after = app.db().groupById(group.getId()).getContacts();
        assertTrue(after.size() == before.size() - 1);
        assertTrue(after.equals(before.without(contact)));
    }
}