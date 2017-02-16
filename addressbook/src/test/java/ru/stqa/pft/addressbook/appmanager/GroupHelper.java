package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.SelenideElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupHelper extends BaseHelper{

    private Groups groupCache = null;

    private void submitGroupCreation() {
        $("input[name='submit']").click();
    }

    private void initGroupCreation() {
        $("input[name='new']").click();
    }

    private void fillGroupForm(GroupData groupData) {
        fill("input[name='group_name']", groupData.getGroupName());
        fill("textarea[name='group_header']", groupData.getGroupHeader());
        fill("textarea[name='group_footer']", groupData.getGroupFooter());
    }

    private void deleteSelectedGroups() {
        $("input[name='delete']").click();
    }

    private void editGroup() {
        $("input[name='edit']").click();
    }

    private void updateGroup() {
        $("input[name='update']").click();
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectedGroupById(group.getId());
        editGroup();
        fillGroupForm(group);
        updateGroup();
        groupCache = null;
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectedGroupById(group.getId());
        deleteSelectedGroups();
        groupCache = null;
        returnToGroupPage();
    }

    public int count(){
        return $$(byName("selected[]")).size();
    }

    private void selectedGroupById(int id) {
        $("input[value='" + id + "']").click();
    }

    private void returnToGroupPage() {
        $(byText("group page")).click();
    }

    public Groups all() {
        if (groupCache != null){
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<SelenideElement> elements = $$("span.group");
        for(SelenideElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.$("input").getValue());
            GroupData group = new GroupData().withId(id).withName(name);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }
}
