import client.UserApiClient;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageObjects.PageForgotPassword;
import pageObjects.PageLogin;
import pageObjects.PageMain;
import pageObjects.PageRegister;
import pojo.UserCreate;
import utils.UserGenerator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
@DisplayName("Вход в аккаунт")
public class LoginTest extends BaseTest {

    @ParameterizedTest(name = "Вход через кнопку 'Войти в аккаунт' [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    void loginViaMainPageButton(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            new PageMain(driver).open()
                    .clickLoginButton()
                    .fillCredentials(user.getEmail(), user.getPassword())
                    .clickLogin();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После входа должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Вход через 'Личный кабинет' [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вход через кнопку 'Личный Кабинет' в шапке")
    void loginViaProfileHeaderButton(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            new PageMain(driver).open()
                    .clickProfileButtonAsGuest()
                    .fillCredentials(user.getEmail(), user.getPassword())
                    .clickLogin();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После входа должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Вход через форму регистрации [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вход через кнопку 'Войти' на странице регистрации")
    void loginViaRegisterPage(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            new PageRegister(driver).open()
                    .clickLoginLink()
                    .fillCredentials(user.getEmail(), user.getPassword())
                    .clickLogin();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После входа должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Вход через форму восстановления пароля [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вход через кнопку 'Войти' на странице восстановления пароля")
    void loginViaForgotPasswordPage(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            new PageForgotPassword(driver).open()
                    .clickLoginLink()
                    .fillCredentials(user.getEmail(), user.getPassword())
                    .clickLogin();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После входа должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }
}
