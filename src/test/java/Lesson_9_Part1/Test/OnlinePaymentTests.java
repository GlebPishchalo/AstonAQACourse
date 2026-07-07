package Lesson_9_Part1.Test;

import Lesson_9_Part1.Pages.OnlinePaymentPage;
import Lesson_9_Part1.Driver.DriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

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
    @DisplayName("Проверка надписей в незаполненных полях для всех вариантов оплаты")
    public void testAllFormPlaceholders() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        
        for (String service : services) {
            page.selectService(service);
            
            Map<String, String> expectedPlaceholders = page.getFormPlaceholders(service);
            assertNotNull(expectedPlaceholders, "Placeholder'ы не найдены для: " + service);
            
            System.out.println("Проверка формы: " + service);
            
            for (Map.Entry<String, String> entry : expectedPlaceholders.entrySet()) {
                String fieldType = entry.getKey();
                String expectedPlaceholder = entry.getValue();
                
                String actualPlaceholder = page.getInputPlaceholder(service, fieldType);
                assertNotNull(actualPlaceholder, "Поле не найдено для: " + service + " - " + fieldType);
                assertEquals(expectedPlaceholder, actualPlaceholder, 
                    "Placeholder не соответствует для: " + service + " - " + fieldType);
                
                System.out.println("  ✅ " + fieldType + ": " + actualPlaceholder);
            }
        }
    }

    @Test
    @Order(5)
    @DisplayName("Проверка работы кнопки 'Продолжить' с выбором 'Услуги связи' и проверка фрейма оплаты")
    public void testContinueButtonWithPaymentFrame() {
        page.selectService("Услуги связи");
        page.enterPhoneNumber("297777777");
        page.enterAmount("10");
        page.enterEmail("test@mail.ru");
        page.clickContinueButton();
        
        boolean paymentFrameVisible = page.isPaymentFrameVisible();
        assertTrue(paymentFrameVisible, "Фрейм оплаты не появился после нажатия 'Продолжить'");
        
        page.switchToPaymentFrame();
        
        String amountInFrame = page.getPaymentAmountInFrame();
        assertNotNull(amountInFrame, "Sum not found");
        assertTrue(amountInFrame.contains("10.00 BYN"), "Dont assert: " + amountInFrame);
        System.out.println("Sum on frame: " + amountInFrame);
        
        String buttonAmount = page.getPaymentButtonAmount();
        assertNotNull(buttonAmount, "Button not found");
        assertTrue(buttonAmount.contains("10.00 BYN"), "Other sum: " + buttonAmount);
        System.out.println("Sum on button: " + buttonAmount);
        
        String phoneInFrame = page.getPhoneNumberInFrame();
        assertNotNull(phoneInFrame, "Phonenumber not found");
        assertTrue(phoneInFrame.contains("375297777777"), "Dont assert: " + phoneInFrame);
        System.out.println("Phonenumber " + phoneInFrame);
        
        List<WebElement> cardLabels = page.getCardInputPlaceholders();
        assertNotNull(cardLabels, "ПFields dor cards not found");
        assertTrue(cardLabels.size() >= 4, "Fields < 4");
        
        String[] expectedLabels = {"Номер карты", "Срок действия", "CVC", "Имя и фамилия на карте"};
        for (String expected : expectedLabels) {
            boolean found = cardLabels.stream()
                .anyMatch(label -> label.getText().contains(expected));
            assertTrue(found, "Not found: " + expected);
            System.out.println("Found: " + expected);
        }
        
        List<WebElement> cardLogos = page.getCardPaymentLogos();
        assertNotNull(cardLogos, "Icons not found");
        assertTrue(cardLogos.size() >= 3, ">3");
        
        String[] expectedLogos = {"visa", "mastercard", "belkart"};
        for (String logo : expectedLogos) {
            boolean found = cardLogos.stream()
                .anyMatch(img -> img.getAttribute("src").toLowerCase().contains(logo));
            assertTrue(found, "Icon not found: " + logo);
            System.out.println("Find icon: " + logo);
        }
        
        page.switchToDefaultContent();
    }
}