package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = new ContactData()
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withHomePhone("+7(111)")
                .withWorkPhone("999 33 33");
        app.contact().create(contact);
        contact = app.contact().all().iterator().next();
        Contacts contacts = app.contact().all();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFormWithPhones(contact.withId(contacts.stream()
                .mapToInt(ContactData::getId)
                .max()
                .getAsInt()));
        System.out.println("allPhones = " + contact.getAllPhones() + " | mergedPhones = " + mergePhones(contactInfoFromEditForm));
        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    }

    private String mergePhones(ContactData contact) {
        return Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                .filter((s) -> ! s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    private static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
