package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageLogin {

    public static final String URL = "https://stellarburgers.education-services.ru/login";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By emailInput = By.xpath(".//label[text()='Email']/../input");
    private final By passwordInput = By.xpath(".//label[text()='Пароль']/../input");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By forgotLink = By.xpath(".//a[text()='Восстановить пароль']");

    public PageLogin(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу входа")
    public PageLogin open() {
        driver.get(URL);
        return this;
    }

    @Step("Ввести email и пароль")
    public PageLogin fillCredentials(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
        return this;
    }

    @Step("Нажать кнопку 'Войти'")
    public PageMain clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new PageMain(driver);
    }

    @Step("Перейти на страницу регистрации")
    public PageRegister clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
        return new PageRegister(driver);
    }

    @Step("Перейти на страницу восстановления пароля")
    public PageForgotPassword clickForgotLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotLink)).click();
        return new PageForgotPassword(driver);
    }
}
