import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test1 {
    ChromeDriver driver;
    WebDriverWait wait;

    @Before
    public void firstTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.yandex.ru");
        String title = driver.getTitle();
        Assert.assertEquals("Яндекс.Почта — бесплатная и надежная электронная почта", title);
        wait = new WebDriverWait(driver, 50);
        driver.findElement(By.xpath("//a[@class='button2 button2_size_mail-big button2_theme_mail-white button2_type_link HeadBanner-Button HeadBanner-Button-Enter with-shadow']")).click();
        driver.findElement(By.xpath("//input[@id=\"passp-field-login\"]")).sendKeys("djeeeelik@yandex.ru");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath("//input[@id='passp-field-passwd']")).sendKeys("2601425Djelmc");
        driver.findElement(By.xpath("//button[@type=\"submit\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@id='nb-3']")));


        //driver.quit();
    }

    @Step("нажать на кнопку 'Все настройки'")
    public void clickSettingsButton() {
        driver.findElement(By.xpath("//a[@id='nb-3']")).click();
        driver.findElement(By.xpath("//span[@class='settings-popup-title-content']")).click();
    }

    @Step("Открыть список языков")
    public void changeLanguageToRus() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")));
        String language = driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).getText();
        if (language.equals("Русский")) {
            System.out.println("Готово");
        } else {
            driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-params='lang=ru']")));
            driver.findElement(By.xpath("//a[@data-params='lang=ru']")).click();
        }
    }

    @Step("Открыть список языков")
    public void changeLanguageToEN() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")));
        String language = driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).getText();
        if (language.equals("English")) {
            System.out.println("Готово");
        } else {
            driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'English')]")));
            driver.findElement(By.xpath("//a[contains(text(),'English')]")).click();
        }
    }


    @Step("Проверка английского языка")
    public void switchOverLanguageTestEng() {
        wait.until(ExpectedConditions.titleIs("Yandex.Mail"));
    }


    @Step("Проверка русского языка")
    public void switchOverLanguageTestRus() {
        wait.until(ExpectedConditions.titleIs("Яндекс.Почта"));
    }

    @Step("активировать все чекбоксы")
    public void activateCheckboxes() {
        driver.findElements(By.xpath("//label[@data-nb='checkbox']")).forEach(WebElement::click);
    }

    @Step("нажать кнопку del на клавиатуре")
    public void deletingMessages() {
        driver.findElement(By.xpath("//a[@id='nb-3']")).sendKeys(Keys.DELETE);
    }

    @Step("Проверка того,что письма удалились")
    public void deletingMessagesTestWithoutClick() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/label[@data-nb='checkbox']")));
    }

    @Step("нажатие на кнопку удалить на верхней панели управления письмами")
    public void clickDeleteMessageButton() {
        driver.findElement(By.xpath("//span[@class='mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete']")).click();
    }

    @Step("проверка того,что письма не удалились")
    public void deletingMessagesTestWithClick() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete']")));
    }

    @Step("клик на кнопку'написать'")
    public void clickComposeButton() {
        driver.findElement(By.xpath("//span[@class='mail-ComposeButton-Text']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mail-Compose-From']//button")));
    }


    @Step("ввести в поле адреса 'кому' емэйл")
    public void sendingMessageTrueEmail() {
        driver.findElement(By.xpath("//div[@name='to']")).click();
        driver.findElement(By.xpath("//div[@name='to']")).sendKeys("djeeeelik@yandex.ru");
    }

    @Step("нажать на кнопку отправить")
    public void clickSendMessageButton() {
        driver.findElement(By.xpath("//button[@id='nb-16']//span[@class='_nb-button-text']")).click();
    }


    @Step("ввести в поле адреса 'кому' случайные символы")
    public void sendingMessageFalseEmail() {
        driver.findElement(By.xpath("//div[@name='to']")).click();
        driver.findElement(By.xpath("//div[@name='to']")).sendKeys("qwerty1122");
    }

    @Step("Проверить, что письмо отправилось")
    public void checkError() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='mail-Done-Title js-title-info']")));
    }

    @Step("Проверить, что емэйл некорректный")
    public void checkError2() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error')]")));
        String message = driver.findElement(By.xpath("//div[contains(@class, 'error')]")).getText();
        Assert.assertEquals("Некорректные адреса: qwerty1122", message);
    }

    @Step("Проверить, что появилась ошибка")
    public void checkError3() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error')]")));
        String message = driver.findElement(By.xpath("//div[contains(@class, 'error')]")).getText();
        Assert.assertEquals("Поле не заполнено. Необходимо ввести адрес.", message);
    }

    @Test
    public void languageEng() {
        clickSettingsButton();
        changeLanguageToEN();
        switchOverLanguageTestEng();
    }

    @Test
    public void languageRus() {
        clickSettingsButton();
        changeLanguageToRus();
        switchOverLanguageTestRus();

    }

    @Test
    public void deletingOne() {
        activateCheckboxes();
        deletingMessages();
        deletingMessagesTestWithoutClick();
    }

    @Test
    public void deletingTwo() {
        activateCheckboxes();
        clickDeleteMessageButton();
        deletingMessagesTestWithoutClick();
    }

    @Test
    public void deletingThree() {
        clickDeleteMessageButton();
        deletingMessagesTestWithClick();
    }

    @Test
    public void sendTheMessage() {
        clickComposeButton();
        sendingMessageTrueEmail();
        clickSendMessageButton();
        checkError();
    }

    @Test
    public void sendTheMessage2() {
        clickComposeButton();
        sendingMessageFalseEmail();
        clickSendMessageButton();
        checkError2();
    }

    @Test
    public void sendTheMessage3() {
        clickComposeButton();
        clickSendMessageButton();
        checkError3();
    }
}