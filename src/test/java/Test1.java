import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import io.qameta.htmlelements.matcher.DisplayedMatcher;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.YandexMail;

import java.util.concurrent.TimeUnit;

import static io.qameta.htmlelements.matcher.HasTextMatcher.hasText;

public class Test1 {
    ChromeDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
    WebPageFactory factory = new WebPageFactory();
    YandexMail ym;

    @BeforeTest(alwaysRun = true)
    public void firstTest() {
        driver = new ChromeDriver();
        ym = factory.get(driver, YandexMail.class);
        ym.getWrappedDriver().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        ym.getWrappedDriver().manage().window().maximize();
        ym.getWrappedDriver().get("https://mail.yandex.ru");
        Assert.assertEquals("Яндекс.Почта — бесплатная и надежная электронная почта", ym.getWrappedDriver().getTitle());
        wait = new WebDriverWait(driver, 50);
        ym.startPage().startButton().click();
        ym.loginPage().loginField().sendKeys("djeeeelik@yandex.ru");
        ym.loginPage().loginMailButton().click();
        ym.loginPage().passwordField().waitUntil(DisplayedMatcher.displayed());
        ym.loginPage().passwordField().sendKeys("2601425Djelmc");
        ym.loginPage().loginMailButton().click();
        ym.lncomingPage().deleteButton().waitUntil("страница не загрузилась", DisplayedMatcher.displayed(), 50);


    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }

    @Step("нажать на кнопку 'Все настройки'")
    public void clickSettingsButton() {
        ym.lncomingPage().settingButton().click();
        ym.popupSetting().settingPopupButton().waitUntil("не открылась страница настроек",
                DisplayedMatcher.displayed(), 25);
        ym.popupSetting().settingPopupButton().click();
    }

    @Step("Открыть меню раскрытия языка")
    public void openLanguageList() {
        ym.settingPage().openLanguageButton().waitUntil((DisplayedMatcher.displayed()));
        ym.settingPage().openLanguageButton().click();
    }


    @Step("Выбрать язык")
    public void changeLanguage(Language language) {
        String languageName = ym.settingPage().openLanguageButton().getText();
        if (language.getName().equals(languageName)) {
            logger.info("Язык не меняется");
        } else {
            ym.settingPage().languageButton(language.getName())
                    .waitUntil("язык не выбрался", DisplayedMatcher.displayed(), 20);
            ym.settingPage().languageButton(language.getName()).click();
        }
    }

    @Step("Проверка языка")
    public void switchOverLanguageTest(Language language) {
        ym.settingPage().openLanguageButton()
                .waitUntil("проверка не произошла", hasText(language.getName()), 25);
    }


    @Step("активировать все чекбоксы")
    public void activateCheckboxes() {
        ym.lncomingPage().checkBoxes().forEach(WebElement::click);
    }

    @Step("нажать кнопку del на клавиатуре")
    public void deletingMessages() {
        ym.lncomingPage().settingButton().sendKeys(Keys.DELETE);
    }

    @Step("Проверка того,что письма удалились")
    public void deletingMessagesTestWithoutClick(String name) {
        if (Integer.parseInt(name) < 30) {
            ym.lncomingPage().quantityMessages().waitUntil("письма не удалились",
                    Matchers.not(DisplayedMatcher.displayed()), 25);
        } else {
            ym.lncomingPage().quantityMessages().waitUntil("письма не удалились", Matchers.not(hasText(name)), 15);
        }
    }

    @Step("нажатие на кнопку удалить на верхней панели управления письмами")
    public void clickDeleteMessageButton() {
        ym.lncomingPage().deleteButton().click();
    }

    @Step("проверка того,что письма не удалились")
    public void deletingMessagesTestWithClick(String name) {
        ym.lncomingPage().quantityMessages().waitUntil("письма не удалились", hasText(name), 15);
    }

    @Step("клик на кнопку'написать'")
    public void clickComposeButton() {
        ym.lncomingPage().composeButton().click();
        ym.sendMessagePage().sendMessageButton().waitUntil("кнопка не появилась", DisplayedMatcher.displayed(), 15);
    }


    @Step("ввести в поле адреса 'кому' емэйл")
    public void sendingMessageEmail(String email) {
        ym.sendMessagePage().sendingMessage().click();
        ym.sendMessagePage().sendingMessage().sendKeys(email);
    }

    @Step("нажать на кнопку отправить")
    public void clickSendMessageButton() {
        ym.sendMessagePage().sendMessageButton().click();
    }


    @Step("Проверить, что письмо отправилось")
    public void checkError() {
        ym.sendMessageDone().waitUntil("письмо отправилось", DisplayedMatcher.displayed(), 25);
    }


    @Step("Проверить, что появилась ошибка")
    public void checkError2(boolean thereWasAnError) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error')]")));
        String message = driver.findElement(By.xpath("//div[contains(@class, 'error')]")).getText();
        if (thereWasAnError) {
            Assert.assertEquals("Поле не заполнено. Необходимо ввести адрес.", message);
        } else {
            Assert.assertEquals("Некорректные адреса: qwerty1122", message);
        }

    }

    @Test(groups = "group1")
    public void languageEng() {
        clickSettingsButton();
        openLanguageList();
        changeLanguage(Language.ENG);
        switchOverLanguageTest(Language.ENG);
    }

    @Test
    public void languageRus() {
        clickSettingsButton();
        openLanguageList();
        changeLanguage(Language.RUS);
        switchOverLanguageTest(Language.RUS);

    }

    @Test
    public void deletingOne() {
        activateCheckboxes();
        String name = ym.lncomingPage().quantityMessages().getText();
        deletingMessages();
        deletingMessagesTestWithoutClick(name);
    }

    @Test
    public void deletingTwo() {
        activateCheckboxes();
        String name = ym.lncomingPage().quantityMessages().getText();
        clickDeleteMessageButton();
        deletingMessagesTestWithoutClick(name);
    }

    @Test
    public void deletingThree() {
        String name = ym.lncomingPage().quantityMessages().getText();
        clickDeleteMessageButton();
        deletingMessagesTestWithClick(name);
    }

    @Test
    public void sendTheMessage() {
        clickComposeButton();
        sendingMessageEmail("djeeeelik@yandex.ru");
        clickSendMessageButton();
        checkError();
    }

    @Test
    public void sendTheMessage2() {
        clickComposeButton();
        sendingMessageEmail("qwerty1122");
        clickSendMessageButton();
        checkError2(false);
    }

    @Test
    public void sendTheMessage3() {
        clickComposeButton();
        sendingMessageEmail("");
        clickSendMessageButton();
        checkError2(true);
    }
}