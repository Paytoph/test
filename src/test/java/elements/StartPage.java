package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface StartPage extends ExtendedWebElement {

    @Description("кнопка войти")
    @FindBy("//div[@class='HeadBanner-ButtonsWrapper']//a[2]")
    ExtendedWebElement startButton();
}
