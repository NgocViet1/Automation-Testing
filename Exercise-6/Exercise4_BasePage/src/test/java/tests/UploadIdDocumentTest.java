package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.ProfilePage;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.*;

public class UploadIdDocumentTest extends BaseTest {

    static ProfilePage profilePage;

    @BeforeAll
    static void setupPage() throws InterruptedException {
        profilePage = new ProfilePage(driver);
        // Truy cập domain để có context
        driver.get("http://localhost:5000");
        Thread.sleep(1000);
        // Set local storage (token, user)
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.setItem('token', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiI0ZWE0ZGU3OS1jOTczLTRmNWQtOWIzOC0yODgzZTkwODFmN2YiLCJlbWFpbCI6InRlc3RAZXhhbXBsZS5jb20iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsIlZyb29tVnJvb21Vc2VySWQiOiI2IiwiZXhwIjoxNzUzMTU3MTkyLCJpc3MiOiJWcm9vbVZyb29tQVBJIiwiYXVkIjoiVnJvb21Wcm9vbUNsaWVudCJ9.a3nLf8Te_66FL31Xxo2miMg2kPsNp4UXWS3_HK_-J1s');"
        );
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.setItem('user', '{\"userId\":6,\"email\":\"test@example.com\",\"phoneNumber\":\"0123456789\",\"fullName\":\"TRƯƠNG NGỌC VIỆT\",\"address\":\"X. Phú Gia, H. Phú Vang, T. Thừa Thiên Huế\",\"creationDate\":\"2025-07-14\",\"emailConfirmed\":false,\"dateOfBirth\":\"2004-02-23\",\"isActive\":false,\"role\":\"admin\",\"driverLicenses\":[]}');"
        );
        Thread.sleep(1000);
        // Sau đó mới vào trang profile
        profilePage.navigate();
        Thread.sleep(1000);
    }

    @Test
    @DisplayName("TC01 - Upload ID Document on Profile Page")
    void testUploadIdDocument() throws InterruptedException {
        // Find the hidden file input
        WebElement uploadInput = driver.findElement(By.cssSelector("input[type='file'][accept*='image']"));
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\test-id.jpg";
        uploadInput.sendKeys(filePath);
        Thread.sleep(2000);
        assertTrue(
                        driver.getPageSource().contains("Review Extracted ID Information"),
                "Upload should be successful and info extracted"
        );
        System.out.println(driver.getPageSource());

    }

    @Test
    @DisplayName("TC02 - Upload too large ID Document")
    void testUploadTooLargeIdDocument() throws InterruptedException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\png-5mb-1.png";
        profilePage.uploadIdDocument(filePath);
        Thread.sleep(2000);

        assertTrue(
                driver.getPageSource().contains("File Too Large") &&
                        driver.getPageSource().contains("Please upload an image smaller than 5MB."),
                "Should show file size error message"
        );

    }
    @Test

    @DisplayName("TC03 - Upload invalid file type (e.g. .txt)")
    void testUploadInvalidFileType() throws InterruptedException {
        WebElement uploadInput = driver.findElement(By.cssSelector("input[type='file'][accept*='image']"));
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\invalidFile.csv";
        uploadInput.sendKeys(filePath);
        Thread.sleep(3000);

        assertTrue(
                driver.getPageSource().contains("Invalid File Type") &&
                        driver.getPageSource().contains("Please upload an image file (JPG, PNG, GIF)."),
                "Should show file type error message"
        );
    }


} 