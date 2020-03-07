package pages;

import elements.*;
import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
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

    @Description("Страница ввода пароля")
    @FindBy("//div[@class, 'mail-App-Content js-mail-app-content']")
    IncomingPage lncomingPage();

    // Всплывающее окно настроек

    @Description("Всплывающее окно настроек")
    @FindBy("//div[@class, 'settings-dropdown']")
    PopupSetting popupSetting();

    // Страница с настройками

    @Description("Страница с настройками")
    @FindBy("//div[@class, 'mail-App-Content js-mail-app-content']")
    SettingPage settingPage();

    // Страница отправки сообщений

    @Description("Страница отправки сообщений")
    @FindBy("//div[@class, 'mail-Layout-Content']")
    SendMessagePage sendMessagePage();
}
