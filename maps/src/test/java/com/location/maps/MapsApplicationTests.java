package com.location.maps;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = ApplicationContext.class)
@PropertySource(value= "classpath:application.properties")
class MapsApplicationTests {

	@Test
	void contextLoads() {
	}
    @Test
    public void testSomething() {
        assertTrue(true);
    }

}
