package ru.stqa.pft.mantis.appmanager;

import com.codeborne.selenide.Configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.close;

public class ApplicationManager {

    private String browser = "";
    private final Properties properties;
    private UserHelper userHelper;
    private MailHelper mailHelper;
    private DbHelper dbHelper;
    private AdminHelper adminHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        Configuration.browser = browser;
    }

    public void stop() {
        close();
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public UserHelper user() {
        if(userHelper == null){
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public MailHelper mail(){
        if(mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public DbHelper db(){
        if(dbHelper == null){
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }

    public AdminHelper admin(){
        if(adminHelper == null){
            adminHelper = new AdminHelper(this);
        }
        return adminHelper;
    }
}
