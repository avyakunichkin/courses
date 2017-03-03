package ru.stqa.pft.addressbook.appmanager;

import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverHelper implements WebDriverProvider {

    private ApplicationManager app;

    public DriverHelper(ApplicationManager app){
        this.app = app;
    }

    @Override
    public WebDriver createDriver(DesiredCapabilities capabilities) {
        capabilities.setBrowserName(System.getProperty("browser"));
        try {
            return new RemoteWebDriver(new URL(app.getProperties().getProperty("selenium.server")), capabilities);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
