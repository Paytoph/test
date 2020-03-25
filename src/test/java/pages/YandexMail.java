package pages;

import elements.*;
import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.extension.page.BaseUrl;

@BaseUrl("https://mail.yandex.ru")
public interface YandexMail extends WebPage {

    @Description("Стартовая страница")
    @FindBy("//div[contains(@class, 'HeadBanner with-')]")
    StartPage startPage();

    @Description("Страница ввода логина и пароля")
    @FindBy("//div[contains(@class, 'passp-auth')]")
    LoginPage loginPage();

    @Description("Страница c входящими сообщениями")
    @FindBy("//div[@class='mail-Layout-Inner']")
    IncomingPage lncomingPage();

    @Description("Всплывающее окно настроек")
    @FindBy("//div[@id='settings-dropdown']")
    PopupSetting popupSetting();

    @Description("Страница с настройками")
    @FindBy("//div")
    SettingPage settingPage();

    @Description("Страница отправки сообщений")
    @FindBy("//div")
    SendMessagePage sendMessagePage();

    @Description("страница, которая подтверждает отправку сообщения")
    @FindBy("//div[@class='mail-Done-Title js-title-info']")
    SendingMessageDone sendingMessageDone();
}
