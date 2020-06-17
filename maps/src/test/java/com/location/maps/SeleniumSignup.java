package com.location.maps;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;


public class SeleniumSignup {
    private SeleniumConfig config;
    private String url = "http://localhost:3000/signup";

    public SeleniumSignup() {
        config = new SeleniumConfig();
        config.getDriver().get(url);
    }

    public void closeWindow() {
        this.config.getDriver().close();
    }

    public String getTitle() {
        return this.config.getDriver().getTitle();
    }

    public String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
    public String GetSignUp() {
        WebElement elementName = this.config.getDriver().findElement(By.name("name"));
        WebElement elementUserName = this.config.getDriver().findElement(By.name("username"));
        WebElement elementEmail = this.config.getDriver().findElement(By.name("email"));
        WebElement elementPassword = this.config.getDriver().findElement(By.name("password"));

        elementName.click();
        elementUserName.click();
        elementEmail.click();
        elementPassword.click();

        elementName.sendKeys(randomString());
        elementUserName.sendKeys(randomString());
        elementEmail.sendKeys(randomString()+ "@gmail.com");
        elementPassword.sendKeys("Secret");

        this.config.getDriver().findElement(By.cssSelector("button")).click();
        WebDriverWait wait = new WebDriverWait(this.config.getDriver(), 5);
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/login"));

        String currentURL = this.config.getDriver().getCurrentUrl();
        return currentURL;
    }

    public String NotSignedUp() {
        WebElement elementName = this.config.getDriver().findElement(By.name("name"));
        WebElement elementUserName = this.config.getDriver().findElement(By.name("username"));
        WebElement elementEmail = this.config.getDriver().findElement(By.name("email"));
        WebElement elementPassword = this.config.getDriver().findElement(By.name("password"));

        elementName.click();
        elementUserName.click();
        elementEmail.click();
        elementPassword.click();

        elementName.sendKeys("Jimmy");
        elementUserName.sendKeys("jimmy");
        elementEmail.sendKeys("jim@morrison.com");
        elementPassword.sendKeys("Secret");

        this.config.getDriver().findElement(By.cssSelector("button")).click();
        WebDriverWait wait = new WebDriverWait(this.config.getDriver(), 5);
        wait.until(ExpectedConditions.urlToBe("http://localhost:3000/signup"));

        String currentURL = this.config.getDriver().getCurrentUrl();
        return currentURL;
    }
}
