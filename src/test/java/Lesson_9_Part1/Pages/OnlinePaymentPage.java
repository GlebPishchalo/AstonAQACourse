package Lesson_9_Part1.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OnlinePaymentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By blockTitle = By.xpath("//section[contains(@class, 'pay')]//h2");
    private final By paymentLogos = By.xpath("//div[contains(@class, 'pay__partners')]//img");
    private final By serviceLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");
    private final By serviceSelect = By.id("pay");
    private final By phoneInput = By.id("connection-phone");
    private final By amountInput = By.id("connection-sum");
    private final By emailInput = By.id("connection-email");
    private final By continueButton = By.xpath("//form[@id='pay-connection']//button[@type='submit']");
    private final By paySection = By.id("pay-section");
    private final By cookieAcceptButton = By.xpath("//button[contains(@class, 'cookie__ok')]");

    public OnlinePaymentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
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

    public String getServiceLinkHref() {
        scrollToPaySection();
        return driver.findElement(serviceLink).getAttribute("href");
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
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(phoneInput));
        input.clear();
        input.sendKeys(phone);
    }

    public void enterAmount(String amount) {
        scrollToPaySection();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(amountInput));
        input.clear();
        input.sendKeys(amount);
    }

    public void enterEmail(String email) {
        scrollToPaySection();
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        input.clear();
        input.sendKeys(email);
    }

    public void clickContinueButton() {
        scrollToPaySection();
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
        System.out.println("Кнопка 'Продолжить' нажата");
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public boolean isPaymentFrameVisible() {
        try {
            By[] frameLocators = {
                By.xpath("//iframe[contains(@src, 'bepaid')]"),
                By.xpath("//iframe[contains(@src, 'checkout.bepaid')]"),
                By.xpath("//iframe[contains(@class, 'bepaid')]"),
                By.xpath("//iframe[@id='bepaid']"),
                By.xpath("//iframe[contains(@src, 'pay')]"),
                By.tagName("iframe")
            };
            
            for (By locator : frameLocators) {
                try {
                    List<WebElement> frames = driver.findElements(locator);
                    if (!frames.isEmpty()) {
                        for (WebElement frame : frames) {
                            String src = frame.getAttribute("src");
                            if (src != null && (src.contains("bepaid") || src.contains("checkout") || src.contains("pay"))) {
                                System.out.println("Найден фрейм оплаты: " + src);
                                return true;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Ошибка при поиске фрейма: " + e.getMessage());
                }
            }
            
            List<WebElement> allFrames = driver.findElements(By.tagName("iframe"));
            System.out.println("Всего iframe на странице: " + allFrames.size());
            
            for (WebElement frame : allFrames) {
                String src = frame.getAttribute("src");
                System.out.println("iframe src: " + src);
                if (src != null && (src.contains("bepaid") || src.contains("checkout") || src.contains("pay"))) {
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
}