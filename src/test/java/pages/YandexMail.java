package pages;

import elements.*;
import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;
import io.qameta.htmlelements.extension.page.BaseUrl;

@BaseUrl("https://mail.yandex.ru")
public interface YandexMail extends WebPage {

    // Стартовая страница

    @Description("Стартовая страница")
    @FindBy("//div[contains(@class, 'HeadBanner with-')]")
    StartPage startPage();

    // Страница ввода логина и пароля

    @Description("Страница ввода логина и пароля")
    @FindBy("//div[contains(@class, 'passp-auth')]")
    LoginPage loginPage();

    // Страница с входящими сообщениями

    @Description("Страница c входящими сообщениями")
    @FindBy("//div[@class='mail-Layout-Inner']")
    IncomingPage lncomingPage();

    // Всплывающее окно настроек

    @Description("Всплывающее окно настроек")
    @FindBy("//div[@id='settings-dropdown']")
    PopupSetting popupSetting();

    // Страница с настройками

    @Description("Страница с настройками")
    @FindBy("//div")
    SettingPage settingPage();

    // Страница отправки сообщений

    @Description("Страница отправки сообщений")
    @FindBy("//div")
    SendMessagePage sendMessagePage();

    // страница, которая подтверждает отправку сообщения

    @Description("страница, которая подтверждает отправку сообщения")
    @FindBy("//div[@class='mail-Done-Title js-title-info']")
    ExtendedWebElement sendMessageDone();
}
