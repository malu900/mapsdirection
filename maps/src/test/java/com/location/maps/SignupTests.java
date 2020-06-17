package com.location.maps;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = ApplicationContext.class
)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = ApplicationContext.class)
@PropertySource(value= "classpath:application.properties")
public class SignupTests {
    private static SeleniumSignup seleniumSignup;
    private String expectedTitle = "React App";
    private String expectedUrl = "http://localhost:3000/login";

    @BeforeAll
    public static void setUp() {
        seleniumSignup = new SeleniumSignup();
    }

    @AfterAll
    public static void tearDown() {
        seleniumSignup.closeWindow();
    }

    @Test
    public void GetTitle() {
        String actualTitle = seleniumSignup.getTitle();
        assertNotNull(actualTitle);
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void WrongTitle() {
        String actualtitle = seleniumSignup.getTitle();
        String wrongTitle = "Something";
        assertNotEquals(wrongTitle, actualtitle);
    }
    @Test
    public void CheckIfRandomStringGenerated() {
        String randomString = seleniumSignup.randomString();
        assertNotNull(randomString);
    }
    @Test
    public void SignUpForm() {
        String getRedirectUrl = seleniumSignup.GetSignUp();
        assertNotNull(getRedirectUrl);
        assertEquals(getRedirectUrl, "http://localhost:3000/login");
    }

}
