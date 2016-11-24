package com.github.luismoramedina;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

import static org.junit.Assert.assertTrue;

/**
 * @author luismoramedina
 */
public class GoogleSearchUnitTest {
	private static final String HUB_URL = "http://localhost:4444/wd/hub";
	private static final DesiredCapabilities CAPABILITIES = DesiredCapabilities.chrome();

	private static RemoteWebDriver driver;
	private static Wait<WebDriver> wait;

	@BeforeClass
	public static void setUp() throws Exception {
		driver = new RemoteWebDriver(new URL(HUB_URL), CAPABILITIES);
		wait = new WebDriverWait(driver, 30);
	}

	@Test
	public void testGoogleSearch() throws Exception {
		driver.get("http://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("selenium grid\n");
		driver.findElement(By.name("q")).submit();

		Boolean ok = wait.until(
				(ExpectedCondition<Boolean>) webDriver -> driver.findElement(By.className("srg")) != null);

		assertTrue(ok);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

}
