package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.ExtendedList;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface IncomingPage
        extends ExtendedWebElement {

    // Кнопка настроек

    @Description("Кнопка настроек")
    @FindBy("//a[contains(@class, 'header-settings nb')]")
    ExtendedWebElement settingButton();

    // Кнопка написать

    @Description("Кнопка написать")
    @FindBy("//span[@class='mail-ComposeButton-Text']")
    ExtendedWebElement composeButton();

    // Чекбоксы

    @Description("Чекбокcы от меня")
    @FindBy("//span[@title='{{ email }}']/../..//label[@data-nb='checkbox']")
    ExtendedList<ExtendedWebElement> checkBoxes(@Param("email") String email);


    // Кнопка удалить на верхней панели

    @Description("Кнопка удалить на верхней панели")
    @FindBy("//span[contains(@class, 'delete')]")
    ExtendedWebElement deleteButton();

    // Подтверждение удаления

    @Description("Подтверждение удаления писем")
    @FindBy("//span[contains(@class, 'button2__text')]/../../button[2]")
    ExtendedWebElement proveDeleting();


    // проверка удаления писем

    @Description("кол-во сообщений")
    @FindBy("//span[@class='mail-NestedList-Item-Info-Link-Text']")
    ExtendedWebElement quantityMessages();

}
