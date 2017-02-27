package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.SelenideElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class ContactHelper extends BaseHelper{

    private Contacts contactsCache = null;

    private void submitCreationContact() {
        $("input[name='submit']").click();
    }

    private void fillContactForm(ContactData contact) {
        fill("input[name='firstname']", contact.getFirstName());
        fill("input[name='lastname']", contact.getLastName());
        fill("textarea[name='address']", contact.getAddress());
        fill("input[name='email']", contact.getEmail());
        fill("input[name='email2']", contact.getEmail2());
        fill("input[name='email3']", contact.getEmail3());
        fill("input[name='home']", contact.getHomePhone());
        fill("input[name='mobile']", contact.getMobilePhone());
        fill("input[name='work']", contact.getWorkPhone());
    }

    public void create(ContactData contact) {
        initContactPage();
        fillContactForm(contact);
        submitCreationContact();
        contactsCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initModificationContactById(contact.getId());
        fillContactForm(contact);
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
        selectContactById(id);
        $("input[value='Delete']").click();
        contactsCache = null;
    }
    public ContactHelper selectContactById(int id){
        $("tr[name='entry'] input[value='" + id + "']").click();
        return this;
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

    public ContactData getContact(Contacts contacts, int id){
        ContactData lastContact = null;
        for(ContactData contact : contacts){
            if (contact.getId() == id){
                lastContact = contact;
                break;
            }
        }
        return lastContact;
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
            String allPhones = element.$("td:nth-of-type(6)").getText();
            contactsCache.add(new ContactData()
                    .withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName)
                    .withAllEmails(getAllEmails(element, "td:nth-of-type(5) a"))
                    .withAllPhones(allPhones));
        }
        return contactsCache;
    }

    private String getAllEmails(SelenideElement element, String selector) {
        String allEmails = "";
        List<String> emails = element.$$(selector).texts();
        for(String email : emails){
            allEmails = allEmails + email;
        }
        return allEmails;
    }

    public ContactData infoFromEditFormWithPhones(int id) {
        initModificationContactById(id);
        String firstName = $(byName("firstname")).getValue();
        String lastName = $(byName("lastname")).getValue();
        String home = $(byName("home")).getValue();
        String mobile = $(byName("mobile")).getValue();
        String work = $(byName("work")).getValue();
        navigator.back();
        return new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoFromEditFormWithEmails(int id) {
        initModificationContactById(id);
        String firstName = $(byName("firstname")).getValue();
        String lastName = $(byName("lastname")).getValue();
        String email = $(byName("email")).getValue();
        String email2 = $(byName("email2")).getValue();
        String email3 = $(byName("email3")).getValue();
        navigator.back();
        return new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    public ContactData infoFromEditForm(int id) {
        initModificationContactById(id);
        String firstName = $(byName("firstname")).getValue();
        String lastName = $(byName("lastname")).getValue();
        String email = $(byName("email")).getValue();
        String email2 = $(byName("email2")).getValue();
        String email3 = $(byName("email3")).getValue();
        String home = $(byName("home")).getValue();
        String mobile = $(byName("mobile")).getValue();
        String work = $(byName("work")).getValue();
        navigator.back();
        return new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                .withEmail(email).withEmail2(email2).withEmail3(email3)
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }

    public ContactData infoFromViewForm(int id) {
        initViewContactById(id);
        String allContact = $("#content").getText();
        navigator.back();
        return new ContactData()
                .withId(id)
                .withAllContact(allContact);
    }

    private void initViewContactById(int id) {
        $("a[href='view.php?id=" + id + "']").click();
    }

    public int getGroupId(ContactData contact, Groups groups) {
        int groupId = -1;
        for (GroupData group : groups){
            if (contact.getGroups().stream().filter((g) -> (g).getId() == group.getId()).count() == 0){
                groupId = group.getId();
                break;
            }
        }
        return groupId;
    }

    public ContactHelper addContactToGroup(int groupId) {
        $("select[name='to_group']").click();
        $(String.format("select[name='to_group'] option[value='%s']", groupId)).click();
        $("input[name='add']").click();
        $(String.format("a[href='./?group=%s']", groupId)).shouldBe(visible);
        return this;
    }

    public ContactHelper openGroup(GroupData group) {
        $("select[name='group']").click();
        $(String.format("select[name='group'] option[value='%s']", group.getId())).click();
        $("input[name='remove']").shouldBe(visible)
                .shouldHave(value(String.format("Remove from \"%s\"", group.getGroupName())));
        return this;
    }

    public ContactHelper deleteContactFromGroup(int id) {
        selectContactById(id);
        $("input[name='remove']").click();
        $(".msgbox").shouldHave(text("Users removed"));
        return this;
    }
}
