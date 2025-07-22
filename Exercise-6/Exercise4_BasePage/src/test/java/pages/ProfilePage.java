 package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProfilePage extends BasePage {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    // Điều hướng đến trang profile
    public void navigate() {
        driver.get("http://localhost:5000/profile");
    }

    // Hàm upload file ảnh
    public void uploadIdDocument(String filePath) {
        WebElement uploadInput = driver.findElement(By.cssSelector("input[type='file'][accept*='image']"));
        uploadInput.sendKeys(filePath);
    }

    // (Có thể bổ sung các hàm khác nếu cần)
}