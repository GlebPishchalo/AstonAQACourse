package Lesson_9_Part1.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OnlinePaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By blockTitle = By.xpath("//section[contains(@class, 'pay')]//h2");
    private final By paymentLogos = By.xpath("//div[contains(@class, 'pay__partners')]//img");
    private final By serviceLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");
    private final By serviceSelect = By.id("pay");
    private final By paySection = By.id("pay-section");
    private final By cookieAcceptButton = By.xpath("//button[contains(@class, 'cookie__ok')]");

    private final String FORM_CONNECTION = "pay-connection";
    private final String FORM_INTERNET = "pay-internet";
    private final String FORM_INSTALMENT = "pay-instalment";
    private final String FORM_ARREARS = "pay-arrears";

    private final Map<String, Map<String, String>> formPlaceholders = new HashMap<>();

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        initializePlaceholders();
    }

    private void initializePlaceholders() {
        Map<String, String> connection = new HashMap<>();
        connection.put("phone", "Номер телефона");
        connection.put("sum", "Сумма");
        connection.put("email", "E-mail для отправки чека");
        formPlaceholders.put("Услуги связи", connection);

        Map<String, String> internet = new HashMap<>();
        internet.put("phone", "Номер абонента");
        internet.put("sum", "Сумма");
        internet.put("email", "E-mail для отправки чека");
        formPlaceholders.put("Домашний интернет", internet);

        Map<String, String> instalment = new HashMap<>();
        instalment.put("score", "Номер счета на 44");
        instalment.put("sum", "Сумма");
        instalment.put("email", "E-mail для отправки чека");
        formPlaceholders.put("Рассрочка", instalment);

        Map<String, String> arrears = new HashMap<>();
        arrears.put("score", "Номер счета на 2073");
        arrears.put("sum", "Сумма");
        arrears.put("email", "E-mail для отправки чека");
        formPlaceholders.put("Задолженность", arrears);
    }

    public void open() {
        driver.get("https://www.mts.by");
        waitForPageLoad();
        acceptCookies();
        scrollToPaySection();
    }

    private void waitForPageLoad() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void acceptCookies() {
        try {
            WebElement acceptBtn = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            acceptBtn.click();
            System.out.println("Куки приняты");
            Thread.sleep(1000);
        } catch (Exception e) {
            try {
                String script = "var cookies = document.querySelector('.cookie');" +
                               "if (cookies) {" +
                               "    var acceptBtn = cookies.querySelector('.cookie__ok');" +
                               "    if (acceptBtn) {" +
                               "        acceptBtn.click();" +
                               "        return true;" +
                               "    }" +
                               "}" +
                               "return false;";
                Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
                if (result) {
                    System.out.println("Куки приняты через JavaScript");
                    Thread.sleep(1000);
                } else {
                    System.out.println("Баннер кук не найден");
                }
            } catch (Exception ex) {
                System.out.println("Не удалось закрыть куки: " + ex.getMessage());
            }
        }
    }

    private void scrollToPaySection() {
        try {
            WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(paySection));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", section);
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
        } catch (Exception e) {
            try {
                WebElement section = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//section[contains(@class, 'pay')]")
                ));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", section);
                Thread.sleep(1000);
                wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
            } catch (Exception ex) {
                throw new RuntimeException("Не удалось найти блок оплаты", ex);
            }
        }
    }

    public String getBlockTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
        String text = title.getText();
        text = text.replace("\n", " ");
        text = text.replace("\r", " ");
        text = text.replaceAll("\\s+", " ");
        return text.trim();
    }

    public List<WebElement> getPaymentLogos() {
        scrollToPaySection();
        return driver.findElements(paymentLogos);
    }

    public String getServiceLinkText() {
        scrollToPaySection();
        return driver.findElement(serviceLink).getText();
    }

    public String getServiceLinkHref() {
        scrollToPaySection();
        return driver.findElement(serviceLink).getAttribute("href");
    }

    public void clickServiceLink() {
        scrollToPaySection();
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(serviceLink));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", link);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        try {
            link.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
        }
    }

    public Map<String, String> getFormPlaceholders(String serviceName) {
        return formPlaceholders.get(serviceName);
    }

    public String getFormId(String serviceName) {
        switch (serviceName) {
            case "Услуги связи":
                return FORM_CONNECTION;
            case "Домашний интернет":
                return FORM_INTERNET;
            case "Рассрочка":
                return FORM_INSTALMENT;
            case "Задолженность":
                return FORM_ARREARS;
            default:
                throw new IllegalArgumentException("Неизвестный сервис: " + serviceName);
        }
    }

    public String getInputPlaceholder(String serviceName, String fieldType) {
        String formId = getFormId(serviceName);
        String fieldId = getFieldId(formId, fieldType);
        WebElement input = wait.until(ExpectedConditions.presenceOfElementLocated(By.id(fieldId)));
        return input.getAttribute("placeholder");
    }

    private String getFieldId(String formId, String fieldType) {
        switch (formId) {
            case FORM_CONNECTION:
                switch (fieldType) {
                    case "phone": return "connection-phone";
                    case "sum": return "connection-sum";
                    case "email": return "connection-email";
                }
                break;
            case FORM_INTERNET:
                switch (fieldType) {
                    case "phone": return "internet-phone";
                    case "sum": return "internet-sum";
                    case "email": return "internet-email";
                }
                break;
            case FORM_INSTALMENT:
                switch (fieldType) {
                    case "score": return "score-instalment";
                    case "sum": return "instalment-sum";
                    case "email": return "instalment-email";
                }
                break;
            case FORM_ARREARS:
                switch (fieldType) {
                    case "score": return "score-arrears";
                    case "sum": return "arrears-sum";
                    case "email": return "arrears-email";
                }
                break;
        }
        throw new IllegalArgumentException("Неизвестное поле: " + fieldType + " для формы: " + formId);
    }

    public void selectService(String serviceName) {
        scrollToPaySection();
        try {
            WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(serviceSelect));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", selectElement);
            Thread.sleep(500);
            
            String script = "var select = document.getElementById('pay');" +
                           "if (select) {" +
                           "    var options = select.getElementsByTagName('option');" +
                           "    for (var i = 0; i < options.length; i++) {" +
                           "        if (options[i].text === '" + serviceName + "') {" +
                           "            select.value = options[i].value;" +
                           "            select.dispatchEvent(new Event('change'));" +
                           "            return true;" +
                           "        }" +
                           "    }" +
                           "}" +
                           "return false;";
            Boolean result = (Boolean) ((JavascriptExecutor) driver).executeScript(script);
            
            if (!result) {
                Select select = new Select(selectElement);
                select.selectByVisibleText(serviceName);
            }
            
            Thread.sleep(500);
        } catch (Exception e) {
            try {
                WebElement selectElement = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//select[@id='pay']")
                ));
                Select select = new Select(selectElement);
                select.selectByVisibleText(serviceName);
            } catch (Exception ex) {
                throw new RuntimeException("Не удалось выбрать услугу: " + serviceName, ex);
            }
        }
    }

    public void enterPhoneNumber(String phone) {
        scrollToPaySection();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-phone")));
        input.clear();
        input.sendKeys(phone);
    }

    public void enterAmount(String amount) {
        scrollToPaySection();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-sum")));
        input.clear();
        input.sendKeys(amount);
    }

    public void enterEmail(String email) {
        scrollToPaySection();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("connection-email")));
        input.clear();
        input.sendKeys(email);
    }

    public void clickContinueButton() {
        scrollToPaySection();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//form[@id='pay-connection']//button[@type='submit']")
        ));
        button.click();
        System.out.println("Кнопка 'Продолжить' нажата");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isPaymentFrameVisible() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            System.out.println("Всего iframe на странице: " + frames.size());
            
            for (WebElement frame : frames) {
                String src = frame.getAttribute("src");
                System.out.println("iframe src: " + src);
                if (src != null && (src.contains("bepaid") || src.contains("checkout.bepaid"))) {
                    System.out.println("Найден фрейм оплаты: " + src);
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Ошибка при поиске фрейма: " + e.getMessage());
            return false;
        }
    }

    public void switchToPaymentFrame() {
        try {
            List<WebElement> frames = driver.findElements(By.tagName("iframe"));
            for (WebElement frame : frames) {
                String src = frame.getAttribute("src");
                if (src != null && (src.contains("bepaid") || src.contains("checkout.bepaid"))) {
                    driver.switchTo().frame(frame);
                    System.out.println("Переключились в фрейм: " + src);
                    Thread.sleep(2000);
                    return;
                }
            }
            throw new RuntimeException("Не удалось найти фрейм оплаты");
        } catch (Exception e) {
            throw new RuntimeException("Не удалось переключиться в фрейм оплаты", e);
        }
    }

    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    public String getPaymentAmountInFrame() {
        try {
            WebElement amount = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'pay-description__cost')]//span")
            ));
            String text = amount.getText().trim();
            System.out.println("Найдена сумма во фрейме: " + text);
            return text;
        } catch (Exception e) {
            try {
                WebElement amount = driver.findElement(
                    By.xpath("//span[contains(@class, 'ng-star-inserted') and contains(text(), 'BYN')]")
                );
                String text = amount.getText().trim();
                System.out.println("Найдена сумма во фрейме (альтернативный локатор): " + text);
                return text;
            } catch (Exception ex) {
                System.out.println("Не удалось найти сумму во фрейме: " + ex.getMessage());
                return null;
            }
        }
    }

    public String getPaymentButtonAmount() {
        try {
            WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//button[contains(@class, 'colored')]//span")
            ));
            String text = button.getText().trim();
            System.out.println("Найдена кнопка оплаты: " + text);
            return text;
        } catch (Exception e) {
            try {
                WebElement button = driver.findElement(
                    By.xpath("//button[contains(text(), 'Оплатить')]//span")
                );
                String text = button.getText().trim();
                System.out.println("Найдена кнопка оплаты (альтернативный локатор): " + text);
                return text;
            } catch (Exception ex) {
                System.out.println("Не удалось найти кнопку оплаты: " + ex.getMessage());
                return null;
            }
        }
    }

    public String getPhoneNumberInFrame() {
        try {
            WebElement phone = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'pay-description__text')]//span")
            ));
            String text = phone.getText().trim();
            System.out.println("Найден номер телефона во фрейме: " + text);
            return text;
        } catch (Exception e) {
            try {
                WebElement phone = driver.findElement(
                    By.xpath("//span[contains(text(), 'Номер:')]")
                );
                String text = phone.getText().trim();
                System.out.println("Найден номер телефона во фрейме (альтернативный локатор): " + text);
                return text;
            } catch (Exception ex) {
                System.out.println("Не удалось найти номер телефона во фрейме: " + ex.getMessage());
                return null;
            }
        }
    }

    public List<WebElement> getCardInputPlaceholders() {
        try {
            return driver.findElements(By.xpath("//app-input//label"));
        } catch (Exception e) {
            System.out.println("Не удалось найти поля для ввода карты: " + e.getMessage());
            return null;
        }
    }

    public List<WebElement> getCardPaymentLogos() {
        try {
            return driver.findElements(By.xpath("//div[contains(@class, 'cards-brands')]//img"));
        } catch (Exception e) {
            System.out.println("Не удалось найти иконки платёжных систем: " + e.getMessage());
            return null;
        }
    }
}