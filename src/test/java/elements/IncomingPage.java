package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

import java.util.List;

public interface IncomingPage {

    // Кнопка настроек

    @Description("Кнопка настроек")
    @FindBy("//a[@id='nb-3']")
    ExtendedWebElement settingButton();

    // Кнопка написать

    @Description("Кнопка написать")
    @FindBy("//span[@class='mail-ComposeButton-Text']")
    ExtendedWebElement composeButton();

    // Чекбоксы

    @Description("чекбоксы")
    @FindBy("//label[@data-nb='checkbox']")
    List<ExtendedWebElement> checkBoxes();

    // Кнопка удалить на верхней панели

    @Description("Кнопка удалить на верхней панели")
    @FindBy("//span[contains(@class, 'delete')]")
    ExtendedWebElement deleteButton();

}
