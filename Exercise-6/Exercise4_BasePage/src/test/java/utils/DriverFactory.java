package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // ❌ KHÔNG tắt JavaScript
        // Map<String, Object> prefs = new HashMap<>();
        // prefs.put("profile.managed_default_content_settings.javascript", 2);
        // options.setExperimentalOption("prefs", prefs);

        // ✅ Bật các tùy chọn hữu ích
        //options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // ✅ Tùy chọn bật/tắt headless (có thể cấu hình linh hoạt sau)
        // options.addArguments("--headless"); // nếu cần test CI/CD

        return new ChromeDriver(options);
    }
}
