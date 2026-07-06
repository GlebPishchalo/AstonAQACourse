package Lesson_9_Part1.Test;

import Lesson_9_Part1.Pages.OnlinePaymentPage;
import Lesson_9_Part1.Driver.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OnlinePaymentTests {
    private OnlinePaymentPage page;
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        page = new OnlinePaymentPage(driver);
        page.open();
    }

    @AfterAll
    public static void tearDown() {
        DriverManager.quitDriver();
    }

    @Test
    @Order(1)
    @DisplayName("Проверка названия блока 'Онлайн пополнение без комиссии'")
    public void testBlockTitle() {
        String expectedTitle = "ОНЛАЙН ПОПОЛНЕНИЕ БЕЗ КОМИССИИ";
        String actualTitle = page.getBlockTitle();
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    @Test
    @Order(2)
    @DisplayName("Проверка наличия логотипов платёжных систем")
    public void testPaymentLogos() {
        int logoCount = page.getPaymentLogos().size();
        assertTrue(logoCount >= 5, "Количество логотипов меньше 5. Найдено: " + logoCount);
        
        String[] expectedLogos = {"Visa", "Verified By Visa", "MasterCard", "MasterCard Secure Code", "Белкарт"};
        for (String logo : expectedLogos) {
            boolean found = page.getPaymentLogos().stream()
                .anyMatch(img -> img.getAttribute("alt").contains(logo));
            assertTrue(found, "Логотип " + logo + " не найден");
        }
    }

  @Test
    @Order(3)
    @DisplayName("Проверка работы ссылки 'Подробнее о сервисе'")
    public void testServiceLink() {
        // Проверяем текст ссылки
        String linkText = page.getServiceLinkText();
        assertNotNull(linkText, "Текст ссылки не найден");
        assertEquals("Подробнее о сервисе", linkText, "Текст ссылки не соответствует ожидаемому");
     
        String href = page.getServiceLinkHref();
        assertNotNull(href, "Ссылка href не найдена");
        String currentUrl = driver.getCurrentUrl();
        page.clickServiceLink();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(currentUrl, newUrl, "URL не изменился после клика по ссылке");
        assertTrue(newUrl.contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"), 
            "URL не содержит ожидаемый путь: " + newUrl);
        driver.navigate().back();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

  
}

    @Test
    @Order(4)
    @DisplayName("Проверка работы кнопки 'Продолжить' с выбором 'Услуги связи'")
    public void testContinueButton() {
        page.selectService("Услуги связи");
        page.enterPhoneNumber("297777777");
        page.enterAmount("10");
        page.enterEmail("test@mail.ru");
        page.clickContinueButton();
        
        boolean paymentFrameVisible = page.isPaymentFrameVisible();
        assertTrue(paymentFrameVisible, "Фрейм оплаты не появился после нажатия 'Продолжить'");
    }
}