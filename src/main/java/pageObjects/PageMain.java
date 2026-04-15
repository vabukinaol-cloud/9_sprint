package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageMain {

    public static final String URL = "https://stellarburgers.education-services.ru";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private final By bunTab = By.xpath(".//span[text()='Булки']");
    private final By sauceTab = By.xpath(".//span[text()='Соусы']");
    private final By fillingTab = By.xpath(".//span[text()='Начинки']");

    public PageMain(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть главную страницу")
    public PageMain open() {
        driver.get(URL);
        return this;
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public PageLogin clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        return new PageLogin(driver);
    }

    @Step("Нажать кнопку 'Личный Кабинет' в шапке")
    public PageLogin clickProfileButtonAsGuest() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        return new PageLogin(driver);
    }

    @Step("Нажать кнопку 'Конструктор' в шапке")
    public PageMain clickConstructorLink() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
        return this;
    }

    @Step("Переключиться на вкладку 'Булки'")
    public PageMain clickBunTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunTab)).click();
        return this;
    }

    @Step("Переключиться на вкладку 'Соусы'")
    public PageMain clickSauceTab() {
        wait.until(ExpectedConditions.elementToBeClickable(sauceTab)).click();
        return this;
    }

    @Step("Переключиться на вкладку 'Начинки'")
    public PageMain clickFillingTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingTab)).click();
        return this;
    }

    @Step("Проверить, что активна вкладка '{section}'")
    public boolean isTabActive(String section) {
        By activeTabWithText = By.xpath(
                "//div[contains(@class,'tab_tab_type_current')]//span[text()='" + section + "']"
        );
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(activeTabWithText));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
