package com.location.maps;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = ApplicationContext.class
)
@TestExecutionListeners( { DependencyInjectionTestExecutionListener.class })
@ContextConfiguration(classes = ApplicationContext.class)
@PropertySource(value= "classpath:application.properties")
public class NotSignedUpTests {

    public static SeleniumSignup signup;

    private String expectedTitle = "React App";

    @BeforeAll
    public static void setUp() {
        System.out.println("Before all test methods");
        signup = new SeleniumSignup();
    }

    @AfterAll
    public static void tearDown() {
        signup.closeWindow();
    }

    @Test
    @DisplayName("First test")
    void firstTest() {
        System.out.println("First test method");
        System.out.println(signup.getTitle());
    }

    @Test
    @DisplayName("Get title test")
    public void GetTitle() {
        String actualTitle = signup.getTitle();
        assertNotNull(actualTitle);
        assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @DisplayName("Not signed in test")
    public void NotSignedIn() {
        String redirectUrl = signup.NotSignedUp();
        assertNotNull(redirectUrl);
        assertNotEquals(redirectUrl, "http://localhost:3000/login");
    }
}
