package selenium.tests;

import base.SeleniumSession;
import base.TestApi;
import base.TestCaseMeta;
import base.checks.SoftAssert;
import base.pages.Article;
import base.pages.SportHp;
import base.pages.Wynikomat;
import org.junit.Test;
import org.openqa.selenium.By;

public class SportHpTest {

    @Test
    @TestCaseMeta(testCaseId = "TC_01",
            testCaseName = "Sprawdzanie elementów",
            description = "Sprawdzenie czy na stronie znajduje się: logo, Wynikomat (LivescoreWynikomat)," +
                    " Minutówka (newsList), Stopka (podzielona na dwa rodzaje ID footer ID)")
    public void whenSportHpAccessedLogoWynikomatMinutowkaStopkaMustBePresent() {
        TestApi.newScenario("whenSportHpAccessedLogoWynikomatMinutowkaStopkaMustBePresent");

        try (SeleniumSession session = new SeleniumSession()) {
            SportHp sportHp = session.sportHp();

            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//img[@alt='Sport.pl']")).isEmpty());
            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//div[@class='LivescoreWynikomat']")).isEmpty());
            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//ul[@class='newsList']")).isEmpty());
            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//footer[@class='page_footer']")).isEmpty());
            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//*[@class='page_footer_upper']")).isEmpty());
            SoftAssert.assertTrue(!sportHp.getDriver().findElements(By.xpath("//*[@class='footer_lower_links']")).isEmpty());
        }

        SoftAssert.checkAssertions();
        TestApi.endScenario();

    }

    @Test
    @TestCaseMeta(testCaseId = "TC_02",
            testCaseName = "Przejście do działu piłka nożna",
            description = "Test ma wejść na dział piłka nożna (jest w nawigacji(, wejść w pierwszy główny artykuł" +
                    " i wyszukać w źródle strony skrypt")
    public void whenSportSectionAccessedThenCheckScriptPresent() {
        TestApi.newScenario("whenSportSectionAccessedThenCheckScriptPresent");

        try (SeleniumSession session = new SeleniumSession()) {
            Article article = session.sportHp().clickPilkaNozna().clickArticle(1);
            SoftAssert.assertTrue(!article.getDriver().findElements(By.xpath("//script[contains(@src,'https://static.im-g.pl/style-modules/master/webpack/Sport/') " +
                    "and contains(@src,'/pagetype7/main.js.jsgz')]")).isEmpty());
        }

        SoftAssert.checkAssertions();
        TestApi.endScenario();

    }

    @Test
    @TestCaseMeta(testCaseId = "TC_03",
            testCaseName = "Wynikomat",
            description = "Test ma wejść na mecz z wynikomatu, oraz sprawdzić czy czerwony pseudo element jest widoczny")
    public void whenWynikomatAccessedThenCheckPseudoElement() {
        TestApi.newScenario("whenWynikomatAccessedThenCheckPseudoElement");

        try (SeleniumSession session = new SeleniumSession()) {
            Wynikomat wynikomat = session.sportHp().clickWynikomat(1);
            SoftAssert.assertTrue(wynikomat.isRedDotPresent());
        }

        SoftAssert.checkAssertions();
        TestApi.endScenario();

    }
}
