package base.pages;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class Article extends Page {
    WebDriver driver;

    public Article(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        try {
            await().atMost(3, SECONDS).until(() -> !driver.findElements(By.xpath("//*[text()='Przejdź do strony »']")).isEmpty());
        } catch (ConditionTimeoutException t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
        }
        acceptPopups(driver);
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(xpath = "//a[@rel='author']")
    public WebElement author;


}