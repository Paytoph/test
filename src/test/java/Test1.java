import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import io.qameta.htmlelements.matcher.DisplayedMatcher;
import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static io.qameta.htmlelements.matcher.HasTextMatcher.hasText;

public class Test1 {
    private static final Logger logger = LoggerFactory.getLogger(Test1.class);
    ChromeDriver driver;
    WebDriverWait wait;
    final WebPageFactory factory = new WebPageFactory();
    YandexMail ym;
    String email = "djeeeelik@yandex.ru";

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        ym = factory.get(driver, YandexMail.class);
        ym.getWrappedDriver().manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
        ym.getWrappedDriver().manage().window().maximize();
        ym.getWrappedDriver().get("https://mail.yandex.ru");
        Assert.assertEquals("Яндекс.Почта — бесплатная и надежная электронная почта", ym.getWrappedDriver().getTitle());
        wait = new WebDriverWait(driver, 50);
        ym.startPage().startButton().click();
        ym.loginPage().loginField().sendKeys(email);
        ym.loginPage().loginMailButton().click();
        ym.loginPage().passwordField().waitUntil("Не открылось окно ввода пароля", DisplayedMatcher.displayed(), 5);
        ym.loginPage().passwordField().sendKeys("2601425Djelmc");
        ym.loginPage().loginMailButton().click();
        ym.lncomingPage()
                .waitUntil("Главная страница не подтверждена", DisplayedMatcher.displayed(), 5);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Step("Нажать на кнопку 'Все настройки'")
    public void clickSettingsButton() {
        ym.lncomingPage().settingButton().click();
        ym.popupSetting().settingPopupButton().waitUntil("Не открылась страница настроек",
                DisplayedMatcher.displayed(), 25);
        ym.popupSetting().settingPopupButton().click();
    }

    @Step("Открыть меню раскрытия языка")
    public void openLanguageList() {
        ym.settingPage().openLanguageButton()
                .waitUntil("Не раскрылось окошко с выбором языка", DisplayedMatcher.displayed(), 5);
        ym.settingPage().openLanguageButton().click();
    }

    @Step("Выбрать язык {language}")
    public void changeLanguage(Language language) {
        String languageName = ym.settingPage().openLanguageButton().getText();
        if
        (language.getTitle().equals(languageName)) {
            logger.info("Язык не меняется");
        } else {
            ym.settingPage().languageButton(language.getName())
                    .waitUntil("ожидание появления списка языков", DisplayedMatcher.displayed(), 25);
            ym.settingPage().languageButton(language.getName()).click();
            logger.info("Язык успешно сменен на: " + languageName);
        }
    }

    @Step("Проверка языка на {language}")
    public void switchOverLanguageTest(Language language) {
        ym.settingPage().openLanguageButton()
                .should("Проверка не произошла", hasText(language.getTitle()), 25);
    }

    private ArrayList<Mes> allMessage() {
        ArrayList<Mes> list = new ArrayList<>();
        int size = ym.lncomingPage().quantityMessages().size();
        for (int i = 0; i < size; i++) {
            list.add(new Mes(ym.lncomingPage().from().get(i),
                    ym.lncomingPage().theme().get(i),
                    ym.lncomingPage().date().get(i),
                    ym.lncomingPage().allCheckboxes().get(i)));
        }
        return list;
    }

    @Step("Активировать все чекбоксы")
    public void activateCheckboxes(List<Mes> list) {
        list.stream().map(Mes::getCheckbox).forEach(WebElement::click);
    }

    @Step("Проверка того,что письма удалились")
    public void deletingMessagesTestWithoutClick(List<Mes> selected, List<Mes> before, List<Mes> after) {
        before.removeAll(selected);
        Assert.assertEquals(after.size(), before.size(), "Не удалились");
        Allure.addAttachment("Проверка удаления писем", "Нечего удалять");
        logger.info("Проверка прошла успешно");
    }

    @Step("Нажатие на кнопку удалить на верхней панели управления письмами")
    public void clickDeleteMessageButton() {
        ym.lncomingPage().deleteButton().click();
    }

    @Step("Проверка активированных чекбоксов")
    public void checkActivatingCheckboxes() {
        ym.lncomingPage().checkBoxes(email)
                .should(Matchers.not(Matchers.emptyIterable()));
    }

    @Step("Клик на кнопку'написать'")
    public void clickComposeButton() {
        ym.lncomingPage().composeButton().click();
        ym.sendMessagePage().sendMessageButton()
                .waitUntil("Кнопка не появилась", DisplayedMatcher.displayed(), 15);
    }

    @Step("Ввести в поле адреса 'кому' емэйл")
    public void sendingMessageEmail(String email) {
        ym.sendMessagePage().sendingMessage().click();
        ym.sendMessagePage().sendingMessage().sendKeys(email);
    }

    @Step("Ввести тему сообщения")
    public void fillTheme() {
        String theme = RandomStringUtils.randomAlphabetic(10);
        ym.sendMessagePage().messageTheme().sendKeys(theme);
    }

    @Step("Нажать на кнопку отправить")
    public void clickSendMessageButton() {
        ym.sendMessagePage().sendMessageButton().click();
    }

    @Step("Проверить, что письмо отправилось")
    public void checkError() {
        ym.sendingMessageDone()
                .waitUntil("Письмо отправилось", DisplayedMatcher.displayed(), 25);
        logger.info("Сообщение успешно отправлено");
    }

    @Step("Проверить, что появилась ошибка")
    public void checkError2(Error error, String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'error')]")));
        String message = driver.findElement(By.xpath("//div[contains(@class, 'error')]")).getText();
        switch (error) {
            case CHECK_ERROR:
                logger.info("Адрес отсутствует");
                Assert.assertEquals("Поле не заполнено. Необходимо ввести адрес.", message);
                break;

            case CHECK_ERROR_2:
                logger.info("Некорректный адрес");
                Assert.assertEquals("Некорректные адреса: " + email, message);
        }
    }

    @Test(groups = "YA-1.1", description = "Переключение языка на английский")
    public void languageEng() {
        clickSettingsButton();
        openLanguageList();
        changeLanguage(Language.ENG);
        switchOverLanguageTest(Language.ENG);
    }

    @Test(groups = "YA-1.2", description = "Переключение языка на русский")
    public void languageRus() {
        clickSettingsButton();
        openLanguageList();
        changeLanguage(Language.RUS);
        switchOverLanguageTest(Language.RUS);
    }

    @Test(groups = "YA-3", description = "Удаление выбранных писем")
    public void deletingTwo() {
        List<Mes> selected = allMessage();
        List<Mes> before = allMessage();
        Random random = new Random();
        random.nextInt(selected.size());
        activateCheckboxes(selected);
        clickDeleteMessageButton();
        ArrayList<Mes> after = allMessage();
        deletingMessagesTestWithoutClick(selected, before, after);
    }

    @Test(groups = "YA-2", description = ("Удаление без выбора писем для удаления"))
    public void deletingThree() {
        List<Mes> selected = new ArrayList<>();
        List<Mes> before = allMessage();
        clickDeleteMessageButton();
        ArrayList<Mes> after = allMessage();
        deletingMessagesTestWithoutClick(selected, before, after);
    }

    @Test(groups = "YA-4", description = ("Выделение писем"))
    public void activatingCheckboxes() {
        checkActivatingCheckboxes();
    }

    @Test(groups = "YA-5", description = "Отправка письма")
    public void sendTheMessage() {
        clickComposeButton();
        sendingMessageEmail(email);
        fillTheme();
        clickSendMessageButton();
        checkError();
    }

    @Test(groups = "YA-6.1", description = "Отправка письма без указания адреса получателя и темы")
    public void sendTheMessage3() {
        clickComposeButton();
        sendingMessageEmail("");
        clickSendMessageButton();
        checkError2(Error.CHECK_ERROR, "");
    }

    @Test(groups = "YA-6.2", description = "Отправка письма без указания адреса получателя")
    public void sendTheMessage4() {
        clickComposeButton();
        sendingMessageEmail("");
        fillTheme();
        clickSendMessageButton();
        checkError2(Error.CHECK_ERROR, "");
    }

    @Test(groups = "YA-6.3", description = "Отправка письма без указания темы")
    public void sendTheMessage5() {
        clickComposeButton();
        sendingMessageEmail(email);
        clickSendMessageButton();
        checkError();
    }

    @Test(groups = "YA-6.4.1", description = "Отправка письма с некорректным адресом получателя (без @)")
    public void sendTheMessage6() {
        clickComposeButton();
        String mail = email.replaceAll("@", "");
        sendingMessageEmail(mail);
        fillTheme();
        clickSendMessageButton();
        checkError2(Error.CHECK_ERROR_2, mail);
    }

    @Test(groups = "YA-6.4.2",
            description = "Отправка письма с некорректным адресом получателя (содержащий @ @)")
    public void sendTheMessage7() {
        clickComposeButton();
        String mail = email.replaceAll("@", "@@");
        sendingMessageEmail(mail);
        fillTheme();
        clickSendMessageButton();
        checkError2(Error.CHECK_ERROR_2, mail);

    }

    @Test(groups = "6.4.3", description = "Отправка письма с некорректным адресом получателя (без домена)")
    public void sendTheMessage8() {
        clickComposeButton();
        sendingMessageEmail(email.split("\\.")[0]);
        fillTheme();
        clickSendMessageButton();
        checkError();
    }
}
