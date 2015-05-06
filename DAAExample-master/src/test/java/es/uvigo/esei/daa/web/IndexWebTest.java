package es.uvigo.esei.daa.web;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import es.uvigo.esei.daa.TestUtils;

public class IndexWebTest {
	private static final int DEFAULT_WAIT_TIME = 1;
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		TestUtils.createFakeContext();
		TestUtils.clearTestDatabase();
	}
	
	@Before
	public void setUp() throws Exception {
		TestUtils.initTestDatabase();
		
		final String baseUrl = "http://localhost:8080/HYS2/";
		
		if (System.getProperty("os.name").toLowerCase().contains("win")) {
			FirefoxBinary binary = new FirefoxBinary(
				new File("FirefoxPortable\\FirefoxPortable.exe"));
			driver = new FirefoxDriver(binary, new FirefoxProfile());
		} else {
			driver = new FirefoxDriver();
		}
		driver.get(baseUrl);
		driver.manage().addCookie(new Cookie("token", "bXJqYXRvOm1yamF0bw=="));
		
		driver.manage().timeouts().implicitlyWait(DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		
		driver.get(baseUrl + "index.html");
	}
	

	@After
	public void tearDown() throws Exception {
		TestUtils.clearTestDatabase();
		
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	
	public void testList() throws Exception {
		verifyXpathCount("//img", 5);
	}
	
	@Test
	public void testListClick() throws Exception {
		WebElement old = driver.findElement(By.name("eventname"));
		String s=old.getAttribute("value");
		WebElement second = driver.findElement(By.id("contenedorDeEventos"));
		second.click();
		waitForTextInElement(By.name("eventname"), "");
		WebElement nuevo = driver.findElement(By.name("eventname"));
		assertNotEquals(s,nuevo.getAttribute("value"));

		
	}

	
	
	private boolean waitForTextInElement(By by, String text) {
	    return new WebDriverWait(driver, DEFAULT_WAIT_TIME)
	    	.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
	}

	private void verifyXpathCount(String xpath, int count) {
		try {
			assertEquals(count, driver.findElements(By.xpath(xpath)).size());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
	}
}
