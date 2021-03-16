package base.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

public abstract class Page {

    public abstract WebDriver getDriver();

    protected void safeClick(WebElement element, WebDriver driver) {
        try {
            element.click();
        } catch (ElementClickInterceptedException t) {
            await().atMost(10, TimeUnit.SECONDS).until(() -> !driver.findElements(By.xpath("//*[text()='Przejdź do strony »']")).isEmpty());
            driver.findElement(By.xpath("//*[text()='Przejdź do strony »']")).click();
            element.click();
        }
    }

    protected void safeClickOld(WebElement element, WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Long currentScreenPosition = (Long) executor.executeScript("return window.pageYOffset;");
        Long innerHeight = (Long) executor.executeScript("return window.innerHeight;");
        long elementPosition = (long) element.getLocation().y;
        if (elementPosition - currentScreenPosition < 300) {
            executor.executeScript("window.scrollBy(0,-300)");
        }
        if (currentScreenPosition + innerHeight - elementPosition < 300 || currentScreenPosition + innerHeight < elementPosition) {
            executor.executeScript("window.scrollBy(0,300)");
        }
        try {
            element.click();

        } catch (ElementClickInterceptedException t) {
            executor.executeScript("window.scrollBy(0,-400)");
        }
    }
}
