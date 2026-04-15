import org.openqa.selenium.WebDriver;
import utils.BrowserFactory;

import java.util.stream.Stream;

public abstract class BaseTest {

    protected WebDriver driver;

    protected static Stream<String> browsers() {
        return Stream.of("chrome", "yandex");
    }

    protected void openBrowser(String browserName) {
        driver = new BrowserFactory().getDriver(browserName);
    }

    protected void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
