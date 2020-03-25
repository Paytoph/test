package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.ExtendedList;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface IncomingPage
        extends ExtendedWebElement {

    @Description("Кнопка настроек")
    @FindBy("//a[contains(@class, 'header-settings nb')]")
    ExtendedWebElement settingButton();

    @Description("Кнопка написать")
    @FindBy("//span[@class='mail-ComposeButton-Text']")
    ExtendedWebElement composeButton();

    @Description("Чекбокcы от меня")
    @FindBy("//span[@title='{{ email }}']/../..//label[@data-nb='checkbox']")
    ExtendedList<ExtendedWebElement> checkBoxes(@Param("email") String email);

    @Description("Кнопка удалить на верхней панели")
    @FindBy("//span[contains(@class, 'delete')]")
    ExtendedWebElement deleteButton();

    @Description("Кол-во сообщений")
    @FindBy("//span[@class='mail-NestedList-Item-Info-Link-Text']")
    ExtendedList<ExtendedWebElement> quantityMessages();

    @Description("Отправитель")
    @FindBy("//span[contains(@class, 'mail-MessageSnippet-FromText')]")
    ExtendedList<ExtendedWebElement> from();

    @Description("Дата")
    @FindBy("//span[contains(@class, 'mail-MessageSnippet-Item_dateText')]")
    ExtendedList<ExtendedWebElement> date();

    @Description("Тема")
    @FindBy("//span[contains(@class, 'message-snippet-subject')]/span[1]/span")
    ExtendedList<ExtendedWebElement> theme();

    @Description("Все чекбоксы")
    @FindBy("//span/../..//label[@data-nb='checkbox']")
    ExtendedList<ExtendedWebElement> allCheckboxes();

}
