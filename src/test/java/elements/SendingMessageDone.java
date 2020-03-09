package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface SendingMessageDone
        extends ExtendedWebElement {

    // страница, которая подтверждает отправку сообщения

    @Description("страница, которая подтверждает отправку сообщения")
    @FindBy("//div[@class='mail-Done-Title js-title-info']")
    ExtendedWebElement sendMessageDone();

}

