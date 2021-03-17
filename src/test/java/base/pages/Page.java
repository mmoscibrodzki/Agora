package base.pages;

import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public abstract class Page {

    public abstract WebDriver getDriver();

    protected void safeClick(WebElement element, WebDriver driver) {
        try {
            acceptPopups(driver);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException t) {
            await().atMost(10, SECONDS).until(() -> isFillerBlockingButtons(driver) || isAddDisplayed(driver));
            acceptPopups(driver);
            element.click();
        }
    }

    public void acceptPopups(WebDriver driver) {
        if (isFillerBlockingButtons(driver)) {
            await().atMost(10, SECONDS).until(() -> !driver.findElements(By.id("onetrust-accept-btn-handler")).isEmpty());
            driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        }

        if (isAddDisplayed(driver)) {
            driver.findElement(By.xpath("//*[text()='Przejdź do strony »']")).click();
        }
        try {
            await().atMost(2, SECONDS).until(() -> !isFillerBlockingButtons(driver) && !isAddDisplayed(driver));
        } catch (ConditionTimeoutException t) {
            acceptPopups(driver);
        }

    }

    private boolean isFillerBlockingButtons(WebDriver driver) {
        if (driver.findElements(By.xpath("//*[@class='onetrust-pc-dark-filter ot-fade-in']")).isEmpty()) {
            return false;
        } else {
            return !driver.findElement(By.xpath("//*[@class='onetrust-pc-dark-filter ot-fade-in']")).getAttribute("style").contains("visibility: hidden;");
        }
    }

    private boolean isAddDisplayed(WebDriver driver) {
        return !driver.findElements(By.xpath("//*[text()='Przejdź do strony »']")).isEmpty();
    }
}
