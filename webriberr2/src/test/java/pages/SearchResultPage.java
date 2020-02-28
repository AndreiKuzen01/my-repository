package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SearchResultPage{

    private final static String SEARCH_RESULT_PAGE_URL = "https://cloud.google.com/";
    private WebDriver driver;

    @FindBy(xpath = "//b[contains(text(), 'Google Cloud Platform Pricing Calculator')]/parent::a")
    private WebElement searchElement;


    public SearchResultPage (WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public SearchResultPage openPage(){
        driver.get(SEARCH_RESULT_PAGE_URL);
        return this;
    }

    public CalculatorPage clickSearchElement(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//b[contains(text(), 'Google Cloud Platform Pricing Calculator')]/parent::a")));
        searchElement.click();
        return new CalculatorPage(driver);
    }
}
