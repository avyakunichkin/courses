package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.testng.AssertJUnit.assertTrue;

public class ContactAddToGroupTests extends TestBase {

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
    public void testContactAddToGroup(){
        int groupId;
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        Groups groups = app.db().groups();
        ContactData contactBefore = app.db().contactById(contact.getId());
        int id = app.contact().getGroupId(contactBefore, groups);
        if (id == -1) {
            app.group().create(new GroupData()
                    .withName("createGroupForAddContact").withHeader("createGroupForAddContact").withFooter("createGroupForAddContact"));
            groupId = app.db().groups().stream()
                    .mapToInt(GroupData::getId)
                    .max()
                    .getAsInt();
        } else {
            groupId = id;
        }
        System.out.println("Contact id = " + contact.getId() + " | Group id = " + groupId);
        app.contact().selectContact(contact)
                .addContactToGroup(groupId);

        int finalGroupId = groupId;
        ContactData contactAfter = app.db().contactById(contact.getId());
        long count = contactAfter.getGroups().stream().filter((g) -> (g).getId() == finalGroupId).count();
        assertTrue( "Count = " + count, count > 0);
    }
}
