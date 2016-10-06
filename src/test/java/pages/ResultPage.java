package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    private WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstHeading")
    private WebElement articleName;

    @FindBy(xpath = "//p[@class='mw-search-nonefound']")
    private WebElement noneFound;

    public String getTextH1() {
        return articleName.getText();
    }

    public String getTextInccorectRequest() {
        return noneFound.getText();
    }

}
