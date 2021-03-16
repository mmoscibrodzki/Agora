package base.pages;

import org.openqa.selenium.WebDriver;

public class Article extends Page {
    WebDriver driver;

    public Article(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }


}