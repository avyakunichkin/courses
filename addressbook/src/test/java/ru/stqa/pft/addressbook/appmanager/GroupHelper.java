package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.GroupData;

import static com.codeborne.selenide.Selenide.$;

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

    public void selectedGroup() {
        $("input[name='selected[]']").click();
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
}
