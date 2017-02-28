package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;

import java.io.IOException;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class AdminResetPasswordTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
        if(app.db().users().stream().filter((u) -> !u.name().equals("administrator")).count() == 0){
            long now = System.currentTimeMillis();
            String email = String.format("user%s@localhost.localdomain", now);
            String user = String.format("user%s", now);
            app.user().registration(user, email);
        }
    }

    @Test
    public void testAdminResetPassword() throws IOException {
        String password = "newpass";
        User user = app.db().users().stream().filter((u) -> !u.name().equals("administrator")).iterator().next();
        app.user().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
        app.admin().openUserById(user.getId())
                .resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.mail().findConfirmationLink(mailMessages, user.email());
        app.user().toLinkConfirm(confirmationLink, password);
        assertTrue(app.newSession().login(user.name(), password));
    }

    @AfterMethod(alwaysRun = true )
    public void stopMailServer() {
        app.mail().stop();
    }
}