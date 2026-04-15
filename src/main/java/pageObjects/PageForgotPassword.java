package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageForgotPassword {

    public static final String URL = "https://stellarburgers.education-services.ru/forgot-password";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public PageForgotPassword(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу восстановления пароля")
    public PageForgotPassword open() {
        driver.get(URL);
        return this;
    }

    @Step("Нажать ссылку 'Войти'")
    public PageLogin clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
        return new PageLogin(driver);
    }
}
