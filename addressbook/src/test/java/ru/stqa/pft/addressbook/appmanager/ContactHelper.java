package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ContactHelper extends BaseHelper{
    public void submitCreationContact() {
        $("input[name='submit']").click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        fill("input[name='firstname']", contactData.getFirstName());
        fill("input[name='lastname']", contactData.getLastName());
        fill("textarea[name='address']", contactData.getAddress());
        fill("input[name='email']", contactData.getEmail());
        fill("input[name='home']", contactData.getHomePhone());

        if(creation){
            if ($$("select[name='new_group'] option").size()>1){
                $("select[name='new_group']").selectOption(1);
            }
        } else {
            Assert.assertFalse($(byName("new_group")).isDisplayed());
        }
    }

    public void createContact(ContactData contactData) {
        initContactPage();
        fillContactForm(contactData, true);
        submitCreationContact();
        returnToHomePage();
    }

    public List<ContactData> getContactList(){
        List<ContactData> contacts = new ArrayList<>();
        List<SelenideElement> elements = $$("tr[name='entry']");
        for(SelenideElement element : elements){
            int id = Integer.parseInt(element.$("input").getValue());
            String firstName = element.$("td:nth-of-type(3)").getText();
            String lastName = element.$("td:nth-of-type(2)").getText();
            ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
            contacts.add(contact);
        }
        return contacts;
    }

    public void initContactPage() {
        $("a[href='edit.php']").click();
    }
    public void initModificationContact(int index) {
        $$("a[href*='edit.php?']").get(index).click();
    }
    public void updateContact() {
        $("input[name='update']").click();
    }
    public void deleteSelectedContacts() {
        $("input[value='Delete']").click();
    }
    public void selectedContact(int index) {
        $$("input[name='selected[]']").get(index).click();
    }
    public boolean isThereAContact() {
        return $("input[name='selected[]']").isDisplayed();
    }
    public void returnToHomePage() {
        $(byText("home page")).click();
    }
}
