package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ContactHelper extends BaseHelper{

    private Contacts contactsCache = null;

    private void submitCreationContact() {
        $("input[name='submit']").click();
    }

    private void fillContactForm(ContactData contactData, boolean creation) {
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

    public void create(ContactData contactData) {
        initContactPage();
        fillContactForm(contactData, true);
        submitCreationContact();
        contactsCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModificationContactById(contact.getId());
        fillContactForm(contact, false);
        updateContact();
        contactsCache = null;
        returnToHomePage();
    }

    public List<ContactData> list(){
        List<ContactData> contacts = new ArrayList<>();
        List<SelenideElement> elements = $$("tr[name='entry']");
        for(SelenideElement element : elements){
            int id = Integer.parseInt(element.$("input").getValue());
            String firstName = element.$("td:nth-of-type(3)").getText();
            String lastName = element.$("td:nth-of-type(2)").getText();
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    private void initContactPage() {
        $("a[href='edit.php']").click();
    }
    private void initModificationContactById(int id) {
        $("a[href='edit.php?id=" + id + "']").click();
    }
    private void updateContact() {
        $("input[name='update']").click();
    }
    public void delete(int id) {
        $("tr[name='entry'] input[value='" + id + "']").click();
        $("input[value='Delete']").click();
        contactsCache = null;
    }
    public boolean isThereAContact() {
        return $("input[name='selected[]']").isDisplayed();
    }
    private void returnToHomePage() {
        $(byText("home page")).click();
    }

    public int count(){
        return $$(byName("selected[]")).size();
    }

    public Contacts all() {
        if (contactsCache != null){
            return new Contacts(contactsCache);
        }
        contactsCache = new Contacts();
        List<SelenideElement> elements = $$("tr[name='entry']");
        for(SelenideElement element : elements){
            int id = Integer.parseInt(element.$("input").getValue());
            String firstName = element.$("td:nth-of-type(3)").getText();
            String lastName = element.$("td:nth-of-type(2)").getText();
            ContactData contact = new ContactData()
                    .withId(id).withFirstName(firstName).withLastName(lastName);
            contactsCache.add(contact);
        }
        return contactsCache;
    }
}
