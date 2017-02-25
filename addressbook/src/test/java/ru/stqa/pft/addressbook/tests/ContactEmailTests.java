package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = new ContactData()
                .withFirstName("testFirstNameForTestEmail")
                .withLastName("testLastNameForTestEmail")
                .withEmail("123")
                .withEmail3("456@email.com");
        app.contact().create(contact);
        Contacts contacts = app.contact().all();
        int maxId = contacts.stream()
                .mapToInt(ContactData::getId)
                .max()
                .getAsInt();
        contact = app.contact().getContact(contacts, maxId);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFormWithEmails(maxId);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s) -> ! s.equals(""))
                .map(ContactEmailTests::cleaned)
                .collect(Collectors.joining(""));
    }

    private static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
