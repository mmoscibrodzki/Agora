package base.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PilkaNozna extends Page {
    WebDriver driver;

    public PilkaNozna(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//section[@class='body']/ul/li[1]/header/h3/a")
    public WebElement firstArticle;

    public Article clickArticle(int articleNumber) {
        WebElement article = driver.findElement(By.xpath("//section[@class='body']/ul/li[" + articleNumber + "]/header/h3/a"));
        safeClick(article, driver);
        return new Article(driver);
    }

}
