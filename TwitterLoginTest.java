package automationFramework;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TwitterLoginTest {
	final static Logger LOGGER = Logger.getLogger("TwitterLoginTest.class");
	final static String USERNAME = "andrewkanestest";
	final static String PASSWORD = "Test1234!";

	static String userNameTextClass = "js-username-field email-input js-initial-focus";
	static String passwordTextClass = "js-password-field";
	static String loginButtonXPath = "//form/div[2]/button";
	static String shortcutsButtonId = "show-shortcuts-btn";
	static String pageTitle = "Twitter";
	static String linkTextClass = "u-linkComplex-target";
	static String bodyLinkText = "andrewkanestest";

	static WebDriver driver;;

	@BeforeClass(alwaysRun = true)
	public void before() {
		// Create a new instance of the Safari driver
		driver = new SafariDriver();
	}

	@AfterClass(alwaysRun = true)
	public void after() {
		// Close the driver
		driver.quit();

		// exit the program explicitly
		System.exit(0);
	}

	@Test
	public static void testTwitterLogin() throws InterruptedException {
		// Launch the twitter login Website
		driver.get("https://twitter.com/login");
		LOGGER.info("Successfully opened the website /twitter.com/login");

		// Wait for 1 Sec
		Thread.sleep(1000);

		// Find username textbox
		WebElement userNameInputElement = driver.findElement(By.className(userNameTextClass));
		userNameInputElement.clear();
		Thread.sleep(1000);
		userNameInputElement.sendKeys(USERNAME);

		// Find password textbox
		WebElement passwordInputElement = driver.findElement(By.className(userNameTextClass));
		passwordInputElement = driver.findElement(By.className(passwordTextClass));
		passwordInputElement.clear();
		Thread.sleep(1000);
		passwordInputElement.sendKeys(PASSWORD);

		Thread.sleep(2000);
		
		WebElement loginButtonElement = driver.findElement(By.className(userNameTextClass));
		loginButtonElement = driver.findElement(By.xpath(loginButtonXPath));
		loginButtonElement.click();

		LOGGER.info("Starting poll for button");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(shortcutsButtonId)));

		String actualTitle = driver.getTitle();
		LOGGER.info("Page title is: " + actualTitle);
		Assert.assertTrue(actualTitle.equals(pageTitle));

		LOGGER.info("Checking for andrewkanestest text on page");
		String bodyText = driver.findElement(By.className(linkTextClass)).getText();
		Assert.assertTrue(bodyText.contains("andrewkanestest"));
	}
}
