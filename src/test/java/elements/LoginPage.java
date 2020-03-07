package elements;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface LoginPage extends ExtendedWebElement {

    // поле ввода логина

    @Description("Поле ввода логина")
    @FindBy("//input[@id='passp-field-login']")
    ExtendedWebElement loginField();

    // поле ввода пароля

    @Description("Поле ввода пароля")
    @FindBy("//input[@id='passp-field-passwd']")
    ExtendedWebElement passwordField();

    // Кнопка для входа в почту

    @Description("Кнопка для перехода в почту")
    @FindBy("//button[@type='submit']")
    ExtendedWebElement loginMailButton();
}
