package ru.stqa.pft.addressbook;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class GroupCreationTests {

    @BeforeMethod
    public void setUp(){
        open("http://127.0.0.1:8080/addressbook/");
        login("admin", "secret");
    }

    @Test
    public void testGroupCreation(){
        initGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData("test1", "test2", "test3"));
        submitGroupCreation();
        initGroupPage();
    }

    private void login(String login, String password) {
        $("input[name='user']").setValue(login);
        $("input[name='pass']").setValue(password);
        $("input[type='submit']").click();
    }

    private void submitGroupCreation() {
        $("input[name='submit']").click();
    }

    private void initGroupCreation() {
        $("input[name='new']").click();
    }

    private void initGroupPage() {
        $("a[href='group.php']").click();
    }

    private void fillGroupForm(GroupData groupData) {
        $("input[name='group_name']").setValue(groupData.getGroupName());
        $("textarea[name='group_header']").setValue(groupData.getGroupHeader());
        $("textarea[name='group_footer']").setValue(groupData.getGroupFooter());
    }

}
