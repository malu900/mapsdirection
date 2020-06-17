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
        String cwd = System.getProperty("user.dir") + "/chromedriver";
        System.setProperty("webdriver.chrome.driver", "/var/jenkins_home/workspace/pipeline-master/./maps/chromedriver");
    }

    public WebDriver getDriver() {
        return driver;
    }
}
