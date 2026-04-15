package pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageProfile {

    public static final String URL = "https://stellarburgers.education-services.ru/account/profile";

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By logoutButton = By.xpath(".//button[text()='Выйти']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']");
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public PageProfile(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открыть страницу профиля")
    public PageProfile open() {
        driver.get(URL);
        return this;
    }

    @Step("Нажать кнопку 'Конструктор' в шапке")
    public PageMain clickConstructorLink() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
        return new PageMain(driver);
    }

    @Step("Нажать на логотип Stellar Burgers")
    public PageMain clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
        return new PageMain(driver);
    }

    @Step("Нажать кнопку 'Выйти'")
    public PageLogin clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
        return new PageLogin(driver);
    }
}
