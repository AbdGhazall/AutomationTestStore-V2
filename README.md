# AutomationTestStore_V2

UI test automation for the **Automation Test Store** site using **Selenium WebDriver**, **Java**, and **TestNG**.  
The suite covers user sign-up, login, cart/checkout navigation, and logout flows with data generated on the fly.

---

## 📂 Project Structure
```
AutomationTestStore_V2/
├─ pom.xml                        # Maven build + deps (Java 17, TestNG, Selenium)
├─ README.md
├─ test-output/                   # TestNG default HTML/XML reports (from prior runs)
└─ src/
   ├─ pages/
   │  ├─ SignupPage.java          # POM for the account creation page
   │  └─ mobrmej.java             # (Unrelated sample class used for practice)
   ├─ tests/
   │  └─ UserTests.java           # End-to-end tests (TestNG)
   └─ utils/
      ├─ DriverFactory.java       # WebDriver lifecycle (Chrome by default)
      └─ TestDataGenerator.java   # Random names/emails/usernames
```

> Notes
> 
> -   `DriverFactory` currently instantiates **`ChromeDriver`** directly and maximizes the window.
>     
> -   `UserTests` uses TestNG and hits real URLs on `automationteststore.com`.
>     
> -   No custom `testng.xml` is present; Maven runs TestNG by scanning annotations.
---

## 🛠️ Tools & Frameworks

-   **Java 17** (configured via `maven-compiler-plugin`)
    
-   **Maven** (project build, dependency management)
    
-   **Selenium WebDriver** (browser automation; Chrome)
    
-   **TestNG 7.11.0** (test runner, assertions, annotations)
    
-   **Page Object Model (POM)** (clean separation of locators & actions)
---

## ✅ Features & Test Scenarios

### 1) Account Creation (Sign-Up)

-   **Page Object:** `pages/SignupPage.java`
    
-   **Flow:** Opens  
    `https://automationteststore.com/index.php?rt=account/create`
    
    -   Fills personal details, email, username, password, agrees to T&C, submits.
        
    -   Success check: page contains **“Your Account Has Been Created!”**.
        
-   **Data:** Generated via `TestDataGenerator` (random first/last name, email, username).
    

### 2) Login (depends on successful sign-up)

-   **Class:** `tests/UserTests.java` (`@Test(priority = 3, dependsOnMethods = {"testSignup"})`)
    
-   **Flow:** Re-uses created credentials to authenticate.
    

### 3) Add to Cart & Navigate to Checkout

-   **Class:** `tests/UserTests.java` (`@Test(priority = 4)`)
    
-   **Flow:** Goes to home `https://automationteststore.com/`, adds an item, proceeds to checkout.
    
-   **Assertion:** URL should contain **`checkout`**.
    

### 4) Logout

-   **Class:** `tests/UserTests.java` (`@Test(priority = 2)`)
    
-   **Flow:** Clicks **Logoff**.
    
-   **Assertion:** Page contains **“You have been logged off your account.”**
    

> The `@BeforeTest` opens the sign-up page; `@AfterTest` quits the driver via `DriverFactory.quitDriver()`.

---

## 📌 Notes

-   **Browser:** Chrome only (as coded). To support other browsers, extend `DriverFactory` to accept a browser type and instantiate the correct driver.
    
-   **Waits:** Current code uses `Thread.sleep(...)` in a few places. For stability, replace with **WebDriverWait** / **ExpectedConditions**.
    
-   **Data collisions:** `TestDataGenerator` appends random numbers; this reduces duplicate user conflicts across runs.
    
-   **Locators & POM:** Keep element locators in `SignupPage` and expose methods only (good encapsulation).
    
-   **Test order:** TestNG priorities and `dependsOnMethods` control execution order; avoid hidden test coupling when possible.

---

## 📸 Screenshots
