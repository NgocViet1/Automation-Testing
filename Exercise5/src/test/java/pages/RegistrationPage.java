package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;

public class RegistrationPage extends BasePage {

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstName = By.id("firstName");
    private By lastName = By.id("lastName");
    private By email = By.id("userEmail");
    private By genderMale = By.xpath("//label[text()='Male']");
    private By genderFemale = By.xpath("//label[text()='Female']");
    private By genderOther = By.xpath("//label[text()='Other']");
    private By mobile = By.id("userNumber");
    private By dobInput = By.id("dateOfBirthInput");
    private By subjects = By.id("subjectsInput");

    private By hobbiesSports = By.xpath("//label[text()='Sports']");
    private By hobbiesReading = By.xpath("//label[text()='Reading']");
    private By hobbiesMusic = By.xpath("//label[text()='Music']");

    private By uploadPicture = By.id("uploadPicture");
    private By address = By.id("currentAddress");

    private By state = By.id("react-select-3-input");
    private By city = By.id("react-select-4-input");

    private By submitBtn = By.id("submit");
    private By confirmationModal = By.id("example-modal-sizes-title-lg");
    private By closeModalBtn = By.id("closeLargeModal");

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String value) {
        type(firstName, value);
    }

    public void enterLastName(String value) {
        type(lastName, value);
    }

    public void enterEmail(String value) {
        type(email, value);
    }

    public void selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male" -> click(genderMale);
            case "female" -> click(genderFemale);
            case "other" -> click(genderOther);
        }
    }

    public void enterMobile(String value) {
        type(mobile, value);
    }

    public void setDateOfBirth(String value) {
        WebElement dob = waitForVisibility(dobInput);
        dob.click();
        dob.sendKeys(Keys.CONTROL + "a");
        dob.sendKeys(value); // Ex: "20 Jul 2025"
        dob.sendKeys(Keys.ENTER);
    }

    public void enterSubject(String subject) {
        WebElement input = waitForVisibility(subjects);
        input.sendKeys(subject);
        input.sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        By locator = switch (hobby.toLowerCase()) {
            case "sports" -> hobbiesSports;
            case "reading" -> hobbiesReading;
            case "music" -> hobbiesMusic;
            default -> throw new IllegalArgumentException("Hobby not recognized");
        };
        scrollToElement(locator); // 👈 thêm dòng này trước khi click
        click(locator);
    }

    public void uploadPicture(String filename) {
        String path = Paths.get("src/test/resources/" + filename).toAbsolutePath().toString();
        type(uploadPicture, path);
    }

    public void enterAddress(String value) {
        type(address, value);
    }

    public void selectState(String value) {
        WebElement input = waitForVisibility(state);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void selectCity(String value) {
        WebElement input = waitForVisibility(city);
        input.sendKeys(value);
        input.sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        WebElement button = waitForVisibility(submitBtn);
        // Scroll to bottom just in case
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    public boolean isConfirmationModalDisplayed() {
        return isElementVisible(confirmationModal);
    }

    public String getConfirmationTitle() {
        return getText(confirmationModal);
    }

    public String getValueFromConfirmationModal(String fieldLabel) {
        By valueLocator = By.xpath("//td[text()='" + fieldLabel + "']/following-sibling::td");
        return getText(valueLocator);
    }

    public void closeModal() {
        click(closeModalBtn);
    }
}
