package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPageTest extends TestBase{

    @Test
    public void testContactInfo() {
        app.goTo().homePage();
        ContactData contact = new ContactData()
                .withFirstName("testFirstName")
                .withLastName("testLastName")
                .withHomePhone("+7(111)")
                .withWorkPhone("999 33 33")
                .withEmail("123")
                .withEmail3("456@email.com");
        app.contact().create(contact);
        int maxId = app.db().contacts().stream()
                .mapToInt(ContactData::getId)
                .max()
                .getAsInt();
        ContactData contactInfoFromViewForm = app.contact().infoFromViewForm(maxId);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(maxId);
        System.out.println("View " + contactInfoFromViewForm + "\nEdit " + contactInfoFromEditForm);
        System.out.println(cleaned(contactInfoFromViewForm.getAllContact()));
        assertThat(cleaned(contactInfoFromViewForm.getAllContact()), equalTo(mergeContactInfo(contactInfoFromEditForm)));
    }

    private String mergeContactInfo(ContactData contact) {
        System.out.println(contact);
        String allContactInfo = Stream.of(contact.getFirstName(), contact.getLastName())
                .filter((s) -> ! s.equals(""))
                .map(ContactPageTest::cleaned)
                .collect(Collectors.joining(""));
        if (contact.getHomePhone()!=null && !contact.getHomePhone().equals("")){
            allContactInfo = allContactInfo + "H:" + cleaned(contact.getHomePhone());
        }
        if (contact.getMobilePhone()!=null && !contact.getMobilePhone().equals("")){
            allContactInfo = allContactInfo + "M:" + cleaned(contact.getMobilePhone());
        }
        if (contact.getWorkPhone()!=null && !contact.getWorkPhone().equals("")){
            allContactInfo = allContactInfo + "W:" + cleaned(contact.getWorkPhone());
        }
        String allEmails = Stream.of(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .filter((s) -> ! s.equals(""))
                .map(ContactPageTest::cleaned)
                .collect(Collectors.joining(""));
        allContactInfo = allContactInfo + allEmails;
        System.out.println(allContactInfo);
        return allContactInfo;
    }

    private static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
