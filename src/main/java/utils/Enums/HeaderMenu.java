package utils.Enums;

public enum HeaderMenu {
    LOGO("//div[@class='header']//a[@class='logo']"),
    SEARCH("//div[@class='header']//a[@href='/search']"),
    LET_THE_CAR_WORK("//div[@class='header']//a[@href='/let-car-work']"),
    TERMS_OF_USE("//div[@class='header']//a[@href='/terms-of-use']"),
    SIGN_UP("//div[@class='header']//a[@href='/registration?url=%2Fsearch']"),
    LOG_IN("//div[@class='header']//a[@href='/login?url=%2Fsearch']"),
    LOG_OUT("//div[@class='header']//a[@href='/logout?url=%2Fsearch']"),
    DELETE_ACCOUNT("//div[@class='header']//a[text()='Delete account']");

    private final String locator;

    HeaderMenu(String locator) {
        this.locator = locator;
    }

    public String getLocator() {
        return locator;
    }
}
