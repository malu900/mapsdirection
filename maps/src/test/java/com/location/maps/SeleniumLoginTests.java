package com.location.maps;



import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ContextConfiguration(classes = ApplicationContext.class)
@PropertySource(value= "classpath:application.properties")
public class SeleniumLoginTests {
    private static SeleniumLogin seleniumLogin;

    @BeforeAll
    public static void setUp() {
        seleniumLogin = new SeleniumLogin();
    }

    @AfterAll
    public static void tearDown() { seleniumLogin.closeWindow(); }

    @Test
    public void TrySignIn() {
        String getRedirectUrl = seleniumLogin.Login();
        assertNotNull(getRedirectUrl);
        assertEquals(getRedirectUrl, "http://localhost:3000/");
    }

}