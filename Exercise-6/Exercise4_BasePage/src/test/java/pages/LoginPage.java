package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By emailField = By.name("email"); // hoặc By.id("email") nếu có id
    private final By passwordField = By.name("password"); // hoặc By.id("password")
    private final By signInButton = By.xpath("//button[contains(.,'Sign In')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("http://localhost:5000/auth/login");
    }

    public void login(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(signInButton).click();
    }
}