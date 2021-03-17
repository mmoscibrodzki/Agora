package base.pages;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class Wynikomat extends Page {

    WebDriver driver;

    public Wynikomat(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        try {
            await().atMost(3, SECONDS).until(() -> !driver.findElements(By.xpath("//*[text()='Przejdź do strony »']")).isEmpty());
        } catch (ConditionTimeoutException t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
        }
        acceptPopups(driver);
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

        String height = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('height');",
                redDotParent).toString();

        String width = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('width');",
                redDotParent).toString();

        String zIndex = ((JavascriptExecutor) driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('z-index');",
                redDotParent).toString();

        return colorRGB.equals("rgb(240, 60, 60)") && height.equals("5px") && width.equals("5px") && zIndex.equals("2");
    }
}
