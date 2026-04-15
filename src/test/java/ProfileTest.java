import client.UserApiClient;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageObjects.PageLogin;
import pageObjects.PageMain;
import pageObjects.PageProfile;
import pojo.UserCreate;
import utils.UserGenerator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
@DisplayName("Личный кабинет и навигация")
public class ProfileTest extends BaseTest {

    private String loginAndGetToMain(UserCreate user) {
        new PageLogin(driver).open()
                .fillCredentials(user.getEmail(), user.getPassword())
                .clickLogin();
        return driver.getCurrentUrl();
    }

    @ParameterizedTest(name = "Переход в личный кабинет [{0}]")
    @MethodSource("browsers")
    @DisplayName("Клик по 'Личный Кабинет' открывает профиль авторизованного пользователя")
    void navigateToProfile(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            loginAndGetToMain(user);
            new PageMain(driver).clickProfileButtonAsGuest();

            assertTrue(driver.getCurrentUrl().contains("account/profile"),
                    "После клика по 'Личный кабинет' должна открыться страница профиля");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Из профиля в конструктор по кнопке [{0}]")
    @MethodSource("browsers")
    @DisplayName("Переход из профиля в конструктор по кнопке 'Конструктор'")
    void navigateFromProfileToConstructorViaButton(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            loginAndGetToMain(user);
            new PageProfile(driver).open()
                    .clickConstructorLink();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После клика по 'Конструктор' должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Из профиля на главную по логотипу [{0}]")
    @MethodSource("browsers")
    @DisplayName("Переход из профиля на главную страницу по клику на логотип")
    void navigateFromProfileToMainViaLogo(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            loginAndGetToMain(user);
            new PageProfile(driver).open()
                    .clickLogo();

            assertEquals(PageMain.URL + "/", driver.getCurrentUrl(),
                    "После клика на логотип должна открыться главная страница");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }

    @ParameterizedTest(name = "Выход из аккаунта [{0}]")
    @MethodSource("browsers")
    @DisplayName("Кнопка 'Выйти' в личном кабинете завершает сессию")
    void logoutFromProfile(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        String accessToken = UserApiClient.createUser(user);
        openBrowser(browser);
        try {
            loginAndGetToMain(user);
            new PageProfile(driver).open()
                    .clickLogout();

            assertTrue(driver.getCurrentUrl().contains("login"),
                    "После выхода должна открыться страница входа");
        } finally {
            closeBrowser();
            UserApiClient.deleteUser(accessToken);
        }
    }
}
