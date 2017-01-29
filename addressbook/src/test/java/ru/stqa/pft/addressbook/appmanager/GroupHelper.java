package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.SelenideElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class GroupHelper extends BaseHelper{

    public void submitGroupCreation() {
        $("input[name='submit']").click();
    }

    public void initGroupCreation() {
        $("input[name='new']").click();
    }

    public void fillGroupForm(GroupData groupData) {
        fill("input[name='group_name']", groupData.getGroupName());
        fill("textarea[name='group_header']", groupData.getGroupHeader());
        fill("textarea[name='group_footer']", groupData.getGroupFooter());
    }

    public void deleteSelectedGroups() {
        $("input[name='delete']").click();
    }

    public void editGroup() {
        $("input[name='edit']").click();
    }

    public void updateGroup() {
        $("input[name='update']").click();
    }

    public void create(GroupData groupData) {
        initGroupCreation();
        fillGroupForm(groupData);
        submitGroupCreation();
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectedGroupById(group.getId());
        editGroup();
        fillGroupForm(group);
        updateGroup();
        returnToGroupPage();
    }

    public void delete(GroupData group) {
        selectedGroupById(group.getId());
        deleteSelectedGroups();
        returnToGroupPage();
    }

    private void selectedGroupById(int id) {
        $("input[value='" + id + "']").click();
    }

    public void returnToGroupPage() {
        $(byText("group page")).click();
    }

    public Groups all() {
        Groups groups = new Groups();
        List<SelenideElement> elements = $$("span.group");
        for(SelenideElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.$("input").getValue());
            GroupData group = new GroupData().withId(id).withName(name);
            groups.add(group);
        }
        return groups;
    }
}
