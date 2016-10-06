
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ResultPage;

import static org.testng.Assert.*;

@Listeners(value = MyListener.class)
public class TestWikipedia {

    private static final String CORRECT_REQUEST = "Ubuntu";
    private static final String INCORRECT_REQUEST = "Сихофазотрон";
    private static final String NO_RESULT = "Соответствий запросу не найдено.";
    private static WebDriver driver = DriverSingletone.getDriver();
    private MainPage mainPage;

    @BeforeMethod
    public void startBrowser() {
        mainPage = new MainPage(driver);
        mainPage.openMainPage();
    }

    @Test
    public void testCorrectRequest() {
        ResultPage resultPage = mainPage.invokeResultPage(CORRECT_REQUEST);
        assertEquals(resultPage.getTextH1(), MainPage.getSeachNote());
    }

    @Test
    public void testIncorrectRequest() {
        ResultPage resultPage = mainPage.invokeResultPage(INCORRECT_REQUEST);
        assertEquals(resultPage.getTextInccorectRequest().trim(), NO_RESULT);
    }

    @Test
    public void noRu100_000() {
        mainPage.clickLanguages();
        assertTrue(mainPage.isLanguageRu());
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

}
