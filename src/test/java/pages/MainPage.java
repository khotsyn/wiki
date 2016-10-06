package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class MainPage {

    private static final String BASE_URL = "https://www.wikipedia.org";
    private static final String SEACH_NOTE = "Ubuntu";
    private WebDriver driver;

    public static String getSeachNote() {
        return SEACH_NOTE;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "searchLanguage")
    private WebElement selectLanguage;

    @FindBy(id = "searchInput")
    private WebElement seachField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement seachButton;

    @FindBy(id = "js-lang-list-button")
    private WebElement listLanguages;

    @FindAll({@FindBy(xpath = "//div[@data-el-section='secondary links'][1]/ul/li/a")})
    private List<WebElement> languages;

    public void openMainPage() {
        driver.get(BASE_URL);
    }

    public ResultPage invokeResultPage(String request) {
        Select select = new Select(selectLanguage);
        select.selectByValue("ru");
        seachField.clear();
        seachField.sendKeys(request);
        seachButton.click();
        return new ResultPage(driver);
    }

    public void clickLanguages() {
        listLanguages.click();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
    }

    public boolean isLanguageRu() {
        List<WebElement> list = new ArrayList<WebElement>();
        list.addAll(languages);
        //System.out.println(list.size());
        for (WebElement elem : list) {
            String language = elem.getText();
            //System.out.println(language);
            if (language.equals("Русский")) {
                return false;
            }
        }
        return true;
    }
}
