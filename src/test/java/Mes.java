import io.qameta.htmlelements.element.ExtendedWebElement;

public class Mes {

    private ExtendedWebElement from;
    private ExtendedWebElement theme;
    private ExtendedWebElement date;
    private ExtendedWebElement checkbox;

    public Mes(ExtendedWebElement from, ExtendedWebElement theme, ExtendedWebElement date, ExtendedWebElement checkbox) {
        this.from = from;
        this.theme = theme;
        this.date = date;
        this.checkbox = checkbox;
    }

    public ExtendedWebElement getFrom() {
        return from;
    }

    public ExtendedWebElement getTheme() {
        return theme;
    }

    public ExtendedWebElement getDate() {
        return date;
    }

    public ExtendedWebElement getCheckbox() {
        return checkbox;
    }
}
