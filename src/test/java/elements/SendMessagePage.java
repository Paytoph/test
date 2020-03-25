package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface SendMessagePage
        extends ExtendedWebElement {

    @Description("кнопка отправить")
    @FindBy("//span[contains(@class, 'User')]/..//button")
    ExtendedWebElement sendMessageButton();

    @Description("поле 'кому'")
    @FindBy("//div[@name='to']")
    ExtendedWebElement sendingMessage();

    @Description(("поле с темой"))
    @FindBy("//input[contains(@name, 'subj')]")
    ExtendedWebElement messageTheme();

    @Description("поле с ошибкой")
    @FindBy("//div[contains(@class, 'error')]")
    ExtendedWebElement errorField();


}
