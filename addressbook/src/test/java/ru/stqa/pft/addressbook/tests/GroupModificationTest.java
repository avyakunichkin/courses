package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTest extends TestBase{

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().selectedGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("testGroupName", "testGroupHeader", "testGroupFooter"));
        app.getGroupHelper().updateGroup();
        app.getNavigationHelper().openGroupPage();
    }
}
