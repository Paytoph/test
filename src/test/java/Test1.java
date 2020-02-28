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
    public void openLanguageList() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")));
        driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
    }

    @Step("Выбрать русский язык")
    public void switchOverLanguageRus() {
        driver.findElement(By.xpath("//a[contains(text(),'English')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'English')]")));
    }

    @Step("активировать все чекбоксы")
    public void deletingMessages() {
        driver.findElements(By.xpath("//label[@data-nb='checkbox']")).forEach(WebElement::click);
    }

    @Step("нажать кнопку del на клавиатуре")
    public void deletingMessagesTestWithoutClick() {
        driver.findElement(By.xpath("//a[@id='nb-3']")).sendKeys(Keys.DELETE);
    }

    @Step("Проверка того,что письма удалились")
    public void assertDeletingMessages() {
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
    }

    @Step("ввести в поле адреса 'кому' емэйл")
    public void sendingMessageTrueEmail() {
        driver.findElement(By.xpath("//input[@id='nb-17']")).sendKeys("djeeeelik@yandex.ru");

    }

    @Test
    public void languageRu() {
        clickSettingsButton();
        openLanguageList();
        switchOverLanguageRus();
    }

    @Test
    public void languageEn() {
        clickSettingsButton();
        openLanguageList();
    }

    @Test
    public void deletingOne() {
        deletingMessages();
        deletingMessagesTestWithoutClick();
        assertDeletingMessages();
    }

    @Test
    public void deletingTwo() {
        deletingMessages();
        clickDeleteMessageButton();
        assertDeletingMessages();
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
    }
}