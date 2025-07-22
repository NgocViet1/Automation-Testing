package tests;

import org.junit.jupiter.api.*;
import pages.LoginPage;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTest extends BaseTest {

    static LoginPage loginPage;

    @BeforeAll
    static void setupPage() {
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("TC01 - Successful login")
    void testLoginSuccess() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);

        loginPage.login("test@example.com", "Password123!");
        Thread.sleep(1000);

        assertTrue(driver.getCurrentUrl().contains("/profile") || driver.getPageSource().contains("Welcome"), "Login successful!");
    }
    @Test
    @DisplayName("TC02 - Login failed (wrong password)")
    void testLoginFail() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);

        loginPage.login("test@example.com", "wrong_password");
        Thread.sleep(1000);

        // Check for error message or still on login page
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Invalid"), "Login failed!");
    }
    @Test
    @DisplayName("TC03 - Wrong email, correct password")
    void testWrongEmail() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("wrongemail@example.com", "Password123!");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Invalid"), "Wrong email, cannot login!");
    }

    @Test
    @DisplayName("TC04 - Wrong email and password")
    void testWrongEmailAndPassword() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("wrongemail@example.com", "wrong_password");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Invalid"), "Wrong email and password, cannot login!");
    }

    @Test
    @DisplayName("TC05 - Empty email")
    void testEmptyEmail() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("", "Password123!");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Email is required"), "Email is required!");
    }

    @Test
    @DisplayName("TC06 - Empty password")
    void testEmptyPassword() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("test@example.com", "");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Password is required"), "Password is required!");
    }

    @Test
    @DisplayName("TC07 - Both fields empty")
    void testEmptyBoth() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("", "");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("required"), "Email and password are required!");
    }

    @Test
    @DisplayName("TC08 - Invalid email format")
    void testInvalidEmailFormat() throws InterruptedException {
        loginPage.navigate();
        Thread.sleep(1000);
        loginPage.login("abc", "Password123!");
        Thread.sleep(1000);
        assertTrue(driver.getCurrentUrl().contains("/login") || driver.getPageSource().contains("Invalid email"), "Invalid email format!");
    }

 
}