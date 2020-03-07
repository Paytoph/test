public enum Language {
    ENG("English", "Yandex.Mail"),
    RUS("Русский", "Яндекс.Почта");
    private final String name;
    private final String title;

    Language(String name, String title) {
        this.name = name;
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }
}
