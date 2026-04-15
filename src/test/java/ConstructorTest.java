import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageObjects.PageMain;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllureJunit5.class)
@DisplayName("Конструктор — разделы ингредиентов")
public class ConstructorTest extends BaseTest {

    @ParameterizedTest(name = "Переход к разделу 'Булки' [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вкладка 'Булки' становится активной после клика")
    void bunsTabBecomesActive(String browser) {
        openBrowser(browser);
        try {
            PageMain page = new PageMain(driver).open();
            page.clickSauceTab();
            page.clickBunTab();

            assertTrue(page.isTabActive("Булки"),
                    "Вкладка 'Булки' должна стать активной");
        } finally {
            closeBrowser();
        }
    }

    @ParameterizedTest(name = "Переход к разделу 'Соусы' [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вкладка 'Соусы' становится активной после клика")
    void saucesTabBecomesActive(String browser) {
        openBrowser(browser);
        try {
            PageMain page = new PageMain(driver).open();
            page.clickSauceTab();

            assertTrue(page.isTabActive("Соусы"),
                    "Вкладка 'Соусы' должна стать активной");
        } finally {
            closeBrowser();
        }
    }

    @ParameterizedTest(name = "Переход к разделу 'Начинки' [{0}]")
    @MethodSource("browsers")
    @DisplayName("Вкладка 'Начинки' становится активной после клика")
    void fillingsTabBecomesActive(String browser) {
        openBrowser(browser);
        try {
            PageMain page = new PageMain(driver).open();
            page.clickFillingTab();

            assertTrue(page.isTabActive("Начинки"),
                    "Вкладка 'Начинки' должна стать активной");
        } finally {
            closeBrowser();
        }
    }
}
