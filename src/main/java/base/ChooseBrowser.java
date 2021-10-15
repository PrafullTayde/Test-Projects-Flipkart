package base;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ChooseBrowser {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    WebDriver driver;
    FileReader inputStream;
    String propFileName = "config.properties";
    public WebDriver getDriver() throws IOException {
        if (webDriver.get() == null) {
            String browsername= getBrowsername();
            if (browsername != null) {
                switch (browsername) {
                    case "firefox":
                        System.out.println("Let's select a firefox browser...");
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        driver.manage().window().maximize();

                        return driver;
                    case "chrome":
                        System.out.println("Let's select a chrome browser...");
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        driver.manage().window().maximize();

                        return driver;
                    default:
                        System.out.println("Browser not supported...");
                }
            } else
                System.out.println("Please provide a Browser name...");

            return webDriver.get();
        }
        return webDriver.get();
    }
public String getBrowsername() throws IOException {
    String browser="";
    try {
        inputStream = new FileReader(propFileName);
        Properties prop = new Properties();
        prop.load(inputStream);

        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }
        // get the property value and print it out
        browser = prop.getProperty("browser_name");
        System.out.println();
    } catch (Exception e) {
        System.out.println("Exception: " + e);
    } finally {
        inputStream.close();
    }
    return browser;
    }

    public String getAppURL() throws IOException {
        String appname="";
        try {


            inputStream = new FileReader(propFileName);
            Properties prop = new Properties();
            prop.load(inputStream);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            appname = prop.getProperty("app_url");
            System.out.println("Application URL:"+appname);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return appname;
    }
    public String[] getIDPass() throws IOException {
        String IDPass[] = new String[2];
        IDPass[0]="";
        IDPass[1]="";
        try {
            inputStream = new FileReader(propFileName);
            Properties prop = new Properties();
            prop.load(inputStream);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out
            IDPass[0]=prop.getProperty("id");
            IDPass[1]=prop.getProperty("pass");

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return IDPass;
    }

}