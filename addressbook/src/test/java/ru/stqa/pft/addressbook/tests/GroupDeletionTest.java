package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTest extends TestBase{

    @Test
    public void testGroupDeletion(){
        app.getNavigationHelper().openGroupPage();
        app.getGroupHelper().selectedGroup();
        app.getGroupHelper().deleteSelectedGroups();
    }
}
