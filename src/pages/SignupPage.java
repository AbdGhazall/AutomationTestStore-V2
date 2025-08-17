package pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

// This class uses the Page Object Model (POM) to represent the Signup Page.
// It contains locators and methods to interact with the form.

public class SignupPage {
	WebDriver driver;
	Random rand = new Random();

	// Constructor - receives WebDriver and assigns it to local variable
	public SignupPage(WebDriver thedriver) {
		this.driver = thedriver;
	}

	// pom ( page object model )
	// Locators
	By firstname = By.id("AccountFrm_firstname");
	By lastname = By.id("AccountFrm_lastname");
	By email = By.id("AccountFrm_email");
	By telephone = By.id("AccountFrm_telephone");
	By fax = By.id("AccountFrm_fax");
	By company = By.id("AccountFrm_company");
	By address1 = By.id("AccountFrm_address_1");
	By address2 = By.id("AccountFrm_address_2");
	By city = By.id("AccountFrm_city");
	By postcode = By.id("AccountFrm_postcode");
	By loginname = By.id("AccountFrm_loginname");
	By password = By.id("AccountFrm_password");
	By confirm = By.id("AccountFrm_confirm");
	By agree = By.id("AccountFrm_agree");
	By continueBtn = By.cssSelector("button[title='Continue']");
	By country = By.id("AccountFrm_country_id");
	By state = By.id("AccountFrm_zone_id");

	// this is to fill the form still we didnt do any test
	public void fillForm(String f, String l, String mail, String phone, String user, String pass)
			throws InterruptedException {
		driver.findElement(firstname).sendKeys(f);
		driver.findElement(lastname).sendKeys(l);
		driver.findElement(email).sendKeys(mail);
		driver.findElement(telephone).sendKeys(phone);
		driver.findElement(fax).sendKeys("9624545755"); // hard-coded
		driver.findElement(company).sendKeys("abc");
		driver.findElement(address1).sendKeys("Amman tlaaelAli");
		driver.findElement(address2).sendKeys("Amman ShafaBadran");
		driver.findElement(city).sendKeys("Amman");

		// Select a random country from dropdown
		Select countrySelect = new Select(driver.findElement(country));
		int countryCount = driver.findElement(country).findElements(By.tagName("option")).size(); // get dropdown
																									// elements size
		countrySelect.selectByIndex(rand.nextInt(1, countryCount)); // index starts at 1 (skip "Please Select")

		Thread.sleep(2000);

		// Select a random state
		Select stateSelect = new Select(driver.findElement(state));
		int stateCount = driver.findElement(state).findElements(By.tagName("option")).size();
		stateSelect.selectByIndex(rand.nextInt(1, stateCount));

		driver.findElement(postcode).sendKeys("3817");
		driver.findElement(loginname).sendKeys(user); // based on the arguments
		driver.findElement(password).sendKeys(pass);
		driver.findElement(confirm).sendKeys(pass);
		driver.findElement(agree).click();
		driver.findElement(continueBtn).click();
	}

	// this is a return function that will return only (true or false since it's a
	// boolean)
	// Verifies if signup was successful by checking page content.
	public boolean isSignupSuccess() {
		return driver.getPageSource().contains("Your Account Has Been Created!");
	}
}
