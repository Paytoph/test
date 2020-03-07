package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface PopupSetting {

    // Кнопка 'Все настройки' в всплывающем окне

    @Description("Кнопка 'Все настройки' в всплывающем окне")
    @FindBy("//span[@class='settings-popup-title-content']")
    ExtendedWebElement settingPopupButton();
}
