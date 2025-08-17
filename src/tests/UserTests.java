package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.SignupPage;
import utils.DriverFactory;
import utils.TestDataGenerator;

// UserTests contains TestNG test cases for signup, login, logout, and checkout

public class UserTests {
	WebDriver driver;
	SignupPage signupPage;
	String firstName; // I will use it later (to be global)
	String lastName;
	String username;
	String password = "Test@1234";

	@BeforeTest
	public void setup() {
		driver = DriverFactory.getDriver();
		driver.get("https://automationteststore.com/index.php?rt=account/create");
		signupPage = new SignupPage(driver);
	}

	@Test(priority = 1)
	public void testSignup() throws InterruptedException {
		firstName = TestDataGenerator.getRandomFirstName();
		lastName = TestDataGenerator.getRandomLastName();
		username = TestDataGenerator.getRandomUsername(firstName, lastName);
		String email = TestDataGenerator.getEmail(firstName, lastName);

		signupPage.fillForm(firstName, lastName, email, "9624545455", username, password);
		Assert.assertTrue(signupPage.isSignupSuccess(), "Signup failed"); // assertTrue (no need for the expected we
																			// just put the actual)
	}

	@Test(priority = 3, dependsOnMethods = { "testSignup" })
	public void testLogin() throws InterruptedException {
		driver.findElement(By.partialLinkText("Login or register")).click();
		driver.findElement(By.id("loginFrm_loginname")).sendKeys(username);
		driver.findElement(By.id("loginFrm_password")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.id("customernav")).getText().contains(firstName), "Login failed");
	}

	@Test(priority = 4, enabled = true)
	public void testAddToCartAndCheckout() throws InterruptedException {
		driver.get("https://automationteststore.com/");
		Thread.sleep(1000);
		List<WebElement> items = driver.findElements(By.className("prdocutname"));
		items.get((int) (Math.random() * items.size())).click(); // random int number based on the all items size
		Thread.sleep(2000);
		WebElement addToCartBtn = driver.findElement(By.className("productpagecart"));
		if (addToCartBtn.getText().equals("Out of Stock")) {
			System.out.println("Item is out of stock");
			return;
		}
		addToCartBtn.click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Checkout")).click();
		Assert.assertTrue(driver.getCurrentUrl().contains("checkout"), "Checkout navigation failed");
	}

	@Test(priority = 2)
	public void testLogout() throws InterruptedException {
		WebElement logoutButton = driver.findElement(By.linkText("Logoff"));
		logoutButton.click();
		Thread.sleep(1000);
		Assert.assertTrue(driver.getPageSource().contains("You have been logged off your account."), "Logout failed");
	}

	@AfterTest
	public void teardown() {
		DriverFactory.quitDriver();
	}
}