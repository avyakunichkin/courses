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
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = new ContactData()
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withEmail("123")
                .withEmail2("432")
                .withEmail3("456@email.com");
        app.contact().create(contact);
        contact = app.contact().all().iterator().next();
        Contacts contacts = app.contact().all();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditFormWithEmails(contact.withId(contacts.stream()
                .mapToInt(ContactData::getId)
                .max()
                .getAsInt()));
        System.out.println("id = " + contact.getId() + " | lastName = " + contact.getLastName() + " | allEmails = " + contact.getAllEmails() + " | mergedEmails = " + mergeEmails(contactInfoFromEditForm));
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
