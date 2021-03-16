package base.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

public class SportHp extends Page {

    WebDriver driver;

    public static final String PATH = "https://www.sport.pl/sport-hp/0,0.html";

    public SportHp(WebDriver driver) {
        this.driver = driver;
        driver.get(PATH);
        PageFactory.initElements(driver, this);
        await().atMost(10, SECONDS).until(() -> !driver.findElements(By.id("onetrust-accept-btn-handler")).isEmpty());
        akceptujeIPrzechodzeDoSerwisu.click();
    }

    @Override
    public WebDriver getDriver() {
        return driver;
    }

    @FindBy(id = "onetrust-accept-btn-handler")
    public WebElement akceptujeIPrzechodzeDoSerwisu;

    @FindBy(xpath = "//a[@title='Piłka nożna 'and text()='Piłka nożna']")
    public WebElement pilkaNozna;

    public PilkaNozna clickPilkaNozna() {
        safeClick(pilkaNozna, driver);
        return new PilkaNozna(driver);
    }

    public Wynikomat clickWynikomat(int wynikomatNumber){
        WebElement wynikomat = driver.findElement(By.xpath("//div[@class='LivescoreWynikomat']/div[@class='swiper-container swiper-container-initialized swiper-container-horizontal']/div[1]/a["+wynikomatNumber+"]"));
        safeClick(wynikomat,driver);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.close();
        driver.switchTo().window(tabs.get(1));
        await().atMost(10, TimeUnit.SECONDS).until(() -> !driver.findElements(By.xpath("//*[text()='Przejdź do strony »']")).isEmpty());
        driver.findElement(By.xpath("//*[text()='Przejdź do strony »']")).click();

        return new Wynikomat(driver);
    }


}
