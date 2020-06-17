package com.location.maps;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.concurrent.TimeUnit;


@Configuration
public class SeleniumConfig {
    private WebDriver driver;

    public SeleniumConfig() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    static {
        File file = new File("/home/maluvdberg/Documents/fhict/repo/fun4/locationbased/maps/chromedriver");
        System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
//        System.setProperty("webdriver.chrome.driver", "chromedriver");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
