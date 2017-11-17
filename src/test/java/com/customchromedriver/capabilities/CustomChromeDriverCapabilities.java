package com.customchromedriver.capabilities;

import net.thucydides.core.util.SystemEnvironmentVariables;
import net.thucydides.core.webdriver.DriverSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import static org.openqa.selenium.remote.CapabilityType.LOGGING_PREFS;

/**
 * Created by analytics on 3/22/2017.
 */
public class CustomChromeDriverCapabilities implements DriverSource {

    @Override
    public WebDriver newDriver() {
        String strRemoteURL = SystemEnvironmentVariables.createEnvironmentVariables().getProperty("remote.url");
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver_win.exe");
        DesiredCapabilities cap = null;
        try {
            cap = DesiredCapabilities.chrome();
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
            cap.setCapability(LOGGING_PREFS, logPrefs);
            cap.setCapability(CapabilityType.PLATFORM, "WINDOWS");
            cap.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            if(strRemoteURL == null)
                return new ChromeDriver(cap);
            else
                return new RemoteWebDriver(new URL(strRemoteURL), cap);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    @Override
    public boolean takesScreenshots() {
        return false;
    }
}
