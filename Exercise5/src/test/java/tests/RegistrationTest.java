package tests;

import org.junit.jupiter.api.*;
import pages.RegistrationPage;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Student Registration Form Tests")
public class RegistrationTest extends BaseTest {

    static RegistrationPage regPage;

    @BeforeAll
    static void setup() {
        regPage = new RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("TC01 - Submit form with valid basic information")
    void testSubmitValidForm() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long@example.com");
        regPage.selectGender("male");
        regPage.enterMobile("0987654321");
        regPage.setDateOfBirth("20 Jul 2025");
        regPage.enterSubject("Math");
        regPage.selectHobby("reading");
        regPage.uploadPicture("test_img.jpg");
        regPage.enterAddress("123 Street, Hanoi");
        regPage.selectState("NCR");
        regPage.selectCity("Delhi");
        regPage.submitForm();

        assertTrue(regPage.isConfirmationModalDisplayed());
        assertEquals("Thanks for submitting the form", regPage.getConfirmationTitle());
    }



    @Test
    @Order(2)
    @DisplayName("TC03 - Submit form with invalid email (negative case)")
    void testSubmitWithInvalidEmail() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long.com");
        regPage.selectGender("male");
        regPage.enterMobile("0987654321");
        regPage.setDateOfBirth("20 Jul 2025");
        regPage.enterSubject("Math");
        regPage.selectHobby("reading");
        regPage.uploadPicture("test_img.jpg");
        regPage.enterAddress("123 Street, Hanoi");
        regPage.selectState("NCR");
        regPage.selectCity("Delhi");
        regPage.submitForm();

        assertFalse(regPage.isConfirmationModalDisplayed());
    }

    @Test
    @Order(3)
    @DisplayName("TC04 - Submit form with all hobbies and multiple subjects")
    void testSubmitWithMultipleHobbiesSubjects() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long@example.com");
        regPage.selectGender("other");
        regPage.enterMobile("0123456789");
        regPage.setDateOfBirth("01 Jan 2000");
        regPage.enterSubject("Math");
        regPage.enterSubject("English");
        regPage.selectHobby("sports");
        regPage.selectHobby("music");
        regPage.uploadPicture("test_img.jpg");
        regPage.enterAddress("456 Another Street");
        regPage.selectState("Uttar Pradesh");
        regPage.selectCity("Lucknow");
        regPage.submitForm();

        assertTrue(regPage.isConfirmationModalDisplayed());
    }

    @Test
    @Order(4)
    @DisplayName("TC05 - Submit form with empty fields (negative case)")
    void testSubmitEmptyForm() {
        regPage.navigate();
        regPage.submitForm();

        assertFalse(regPage.isConfirmationModalDisplayed());
    }

    @Test
    @Order(5)
    @DisplayName("TC06 - Submit form with invalid mobile number (negative case)")
    void testSubmitWithInvalidMobile() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long@example.com");
        regPage.selectGender("male");
        regPage.enterMobile("12345abc"); // Số điện thoại không hợp lệ
        regPage.setDateOfBirth("01 Jan 2000");
        regPage.enterSubject("Math");
        regPage.selectHobby("reading");
        regPage.uploadPicture("test_img.jpg");
        regPage.enterAddress("123 Street, Hanoi");
        regPage.selectState("NCR");
        regPage.selectCity("Delhi");
        regPage.submitForm();

        assertFalse(regPage.isConfirmationModalDisplayed());
    }

    @Test
    @Order(6)
    @DisplayName("TC07 - Submit form with very long address")
    void testSubmitWithLongAddress() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long@example.com");
        regPage.selectGender("male");
        regPage.enterMobile("0987654321");
        regPage.setDateOfBirth("01 Jan 2000");
        regPage.enterSubject("Math");
        regPage.selectHobby("reading");
        regPage.uploadPicture("test_img.jpg");
        // Địa chỉ rất dài
        String longAddress = "A".repeat(500);
        regPage.enterAddress(longAddress);
        regPage.selectState("NCR");
        regPage.selectCity("Delhi");
        regPage.submitForm();


        assertTrue(regPage.isConfirmationModalDisplayed());
    }

    @Test
    @Order(7)
    @DisplayName("TC08 - Submit form with future date of birth (negative case)")
    void testSubmitWithFutureDOB() {
        regPage.navigate();
        regPage.enterFirstName("Long");
        regPage.enterLastName("Nguyen");
        regPage.enterEmail("long@example.com");
        regPage.selectGender("male");
        regPage.enterMobile("0987654321");
        regPage.setDateOfBirth("20 Jul 2100"); // Ngày sinh ở tương lai
        regPage.enterSubject("Math");
        regPage.selectHobby("reading");
        regPage.uploadPicture("test_img.jpg");
        regPage.enterAddress("123 Street, Hanoi");
        regPage.selectState("NCR");
        regPage.selectCity("Delhi");
        regPage.submitForm();

        assertTrue(regPage.isConfirmationModalDisplayed());
    }


}
