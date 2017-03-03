package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

public class ApplicationManager {

    private String browser = "";
    private final Properties properties;
    private ContactHelper contactHelper = new ContactHelper();
    private GroupHelper groupHelper = new GroupHelper();
    private NavigationHelper navigationHelper = new NavigationHelper();
    private SessionHelper sessionHelper = new SessionHelper();
    private DbHelper dbHelper;

    public ApplicationManager(String browser){
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        dbHelper = new DbHelper();
        if ("".equals(properties.getProperty("selenium.server"))){
            Configuration.browser = browser;
        }else{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(System.getProperty("browser"));
            try {
                new RemoteWebDriver(new URL("http://172.16.101.70:4444/wd/hub"), capabilities);
            }
            catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        open(properties.getProperty("web.baseUrl"));
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
    }

    public void stop() {
        close();
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public DbHelper db() {
        return dbHelper;
    }
}
