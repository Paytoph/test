package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface SendMessagePage {

    // кнопка отправить

    @Description("кнопка отправить")
    @FindBy("//span[contains(@class, 'User')]/..//button")
    ExtendedWebElement sendMessageButton();

    // поле 'кому'

    @Description("поле 'кому'")
    @FindBy("//div[@name='to']")
    ExtendedWebElement sendingMessage();

    // поле с ошибкой

    @Description("поле с ошибкой")
    @FindBy("//div[contains(@class, 'error')]")
    ExtendedWebElement errorField();


}
