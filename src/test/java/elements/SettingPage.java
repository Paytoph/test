package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface SettingPage
        extends ExtendedWebElement {

    @Description("Кнопка раскрытия языка")
    @FindBy("//span[@class='b-selink__link mail-Settings-Lang']")
    ExtendedWebElement openLanguageButton();

    @Description("Кнопка смены языка, так же показывает текущий язык")
    @FindBy("//a[contains(@data-params, '{{ language }}')]")
    ExtendedWebElement languageButton(@Param("language") String language);
}
