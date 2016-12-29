package ru.stqa.pft.addressbook.appmanager;

import ru.stqa.pft.addressbook.model.GroupData;

import static com.codeborne.selenide.Selenide.$;

public class GroupHelper {
    public void submitGroupCreation() {
        $("input[name='submit']").click();
    }

    public void initGroupCreation() {
        $("input[name='new']").click();
    }

    public void fillGroupForm(GroupData groupData) {
        $("input[name='group_name']").setValue(groupData.getGroupName());
        $("textarea[name='group_header']").setValue(groupData.getGroupHeader());
        $("textarea[name='group_footer']").setValue(groupData.getGroupFooter());
    }
}
