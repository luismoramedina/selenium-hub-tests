package com.github.luismoramedina;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

/**
 * @author luismoramedina
 */
@RunWith(Parameterized.class)
public class GoogleSearchUnitTest {

	private static final String HUB_URL = "http://localhost:4444/wd/hub";

	private static RemoteWebDriver driver;
	private static Wait<WebDriver> wait;
	private String browser;

	public GoogleSearchUnitTest(String browser){
		this.browser = browser;

	}

	@Before
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = null;
		if (this.browser.equals("Chrome")) {
			capabilities = DesiredCapabilities.chrome();
		} else if (browser.equals("FF")) {
			capabilities = DesiredCapabilities.firefox();
		}

		System.out.println("capabilities = " + capabilities);
		driver = new RemoteWebDriver(new URL(HUB_URL), capabilities);
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

	@After
	public void tearDown() throws Exception {
		try {
			driver.close();
			driver.quit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameterized.Parameters
	public static Collection getBrowser(){
		return Arrays.asList("FF", "Chrome");
	}



}
