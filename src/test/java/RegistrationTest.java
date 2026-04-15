import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.extension.ExtendWith;
import pageObjects.PageRegister;
import utils.UserGenerator;
import pojo.UserCreate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
@DisplayName("Регистрация")
public class RegistrationTest extends BaseTest {

    @ParameterizedTest(name = "Успешная регистрация [{0}]")
    @MethodSource("browsers")
    @DisplayName("Успешная регистрация перенаправляет на страницу входа")
    void successfulRegistrationRedirectsToLogin(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        openBrowser(browser);
        try {
            new PageRegister(driver).open()
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setPassword(user.getPassword())
                    .clickRegisterButton();

            assertTrue(driver.getCurrentUrl().contains("/login"),
                    "После регистрации должен быть переход на страницу входа");
        } finally {
            closeBrowser();
        }
    }

    @ParameterizedTest(name = "Ошибка при коротком пароле [{0}]")
    @MethodSource("browsers")
    @DisplayName("Пароль короче 6 символов вызывает ошибку")
    void shortPasswordShowsError(String browser) {
        UserCreate user = UserGenerator.getRandomUser();
        openBrowser(browser);
        try {
            String errorText = new PageRegister(driver).open()
                    .setName(user.getName())
                    .setEmail(user.getEmail())
                    .setPassword("12345")
                    .clickRegisterButton()
                    .getPasswordErrorText();

            assertEquals("Некорректный пароль", errorText,
                    "При вводе пароля меньше 6 символов должна появиться ошибка");
        } finally {
            closeBrowser();
        }
    }
}
