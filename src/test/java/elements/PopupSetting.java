package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface PopupSetting
        extends ExtendedWebElement {

    @Description("Кнопка 'Все настройки' в всплывающем окне")
    @FindBy("//span[@class='settings-popup-title-content']")
    ExtendedWebElement settingPopupButton();
}
