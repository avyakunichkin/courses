package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class ContactCreationTest {

    @BeforeMethod
    public void setUp(){
        open("http://127.0.0.1:8080/addressbook/");
        login("admin", "secret");
    }

    @Test
    public void testContactCreation(){
        initContactPage();
        fillContactForm(new ContactData("testFirstName", "testLastName", "testAddress", "testEmail", "testHomePhone"));
        submitCreationContact();
        openHomePage();
    }

    private void openHomePage() {
        $("a[href='./']").click();
    }

    private void submitCreationContact() {
        $("input[type='submit']").click();
    }

    private void fillContactForm(ContactData contactData) {
        $("input[name='firstname']").setValue(contactData.getFirstName());
        $("input[name='lastname']").setValue(contactData.getLastName());
        $("textarea[name='address']").setValue(contactData.getAddress());
        $("input[name='email']").setValue(contactData.getEmail());
        $("input[name='home']").setValue(contactData.getHomePhone());
    }

    private void login(String login, String password) {
        $("input[name='user']").setValue(login);
        $("input[name='pass']").setValue(password);
        $("input[type='submit']").click();
    }

    private void initContactPage() {
        $("a[href='edit.php']").click();
    }
}
