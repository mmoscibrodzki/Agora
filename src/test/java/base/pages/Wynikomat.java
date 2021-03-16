package base.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Wynikomat extends Page {

    WebDriver driver;

    public Wynikomat(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "div.line.correctTime")
    public WebElement redDotParent;

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    public Boolean isRedDotPresent() {
        String colorRGB = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('background-color');",
                redDotParent).toString();

        return colorRGB.contains("rgb(240, 60, 60)");
    }
}
