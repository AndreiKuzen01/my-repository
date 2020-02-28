package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalculatorPage {

    private static final String URL = "https://cloud.google.com/products/calculator";
    private WebDriver driver;



    @FindBy(xpath = "//md-tabs-wrapper//md-tab-item//div[@class='name ng-binding']")
    private List<WebElement> sectionsEngine;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstance;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operationSystemContainer;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-content/md-option/div")
    private List<WebElement> operationSystemList;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement vmClassContainer;

    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']//md-content/md-option/div")
    private List<WebElement> vmClassList;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement instanceTypeContainer;

    @FindBy(xpath = "//div/md-select-menu[@class='md-overflow']//md-option")
    private List<WebElement> instanceTypeList;

    @FindBy(xpath = "//div[@layout='row']//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement addGpuCheckbox;

    @FindBy(xpath = "//md-select-value/parent::md-select[@placeholder='Number of GPUs']")
    private WebElement numberOfGPUsContainer;

    @FindBy(xpath = "//div//md-option[@ng-disabled='item.value != 0 && item.value < listingCtrl.minGPU']")
    private List<WebElement> numberOfJPUList;

    public CalculatorPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CalculatorPage switchToFrame() {
        driver.switchTo().frame(0);
        return this;
    }

    public void outFromFrame(){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
       driver.switchTo().defaultContent();
    }

    public void openPage(){
        driver.get(URL);
    }

    public CalculatorPage setEngine(String engineName) {

        setOptionFromListByEquals(sectionsEngine,engineName.toUpperCase());
        return this;
    }

    public CalculatorPage setNumberOfInstance(int number) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@ng-model='listingCtrl.computeServer.quantity']")));
        numberOfInstance.click();
        numberOfInstance.sendKeys(String.valueOf(number));
        return this;
    }

    public CalculatorPage setOperationSystem(String operationSystem){
        operationSystemContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfAllElements(operationSystemList));
        setOptionFromListByContains(operationSystemList, operationSystem);
        return this;
    }

    public CalculatorPage setVMClass(String vmClass){
        vmClassContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfAllElements(vmClassList));
        setOptionFromListByContains(vmClassList, vmClass);
        return this;
    }

    public CalculatorPage setInstanceType(String instanceType){
        instanceTypeContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfAllElements(instanceTypeList));
        setOptionFromListByContains(instanceTypeList,instanceType);
        return this;
    }

    public CalculatorPage addJPUs(){
        addGpuCheckbox.click();
        return this;
    }

    public CalculatorPage setNumberOfJPU(String number){
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-select-value/parent::md-select[@placeholder='Number of GPUs']")));
        numberOfGPUsContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfAllElements(numberOfJPUList));
        setOptionFromListByContains(numberOfJPUList, number);
        return this;
    }

    private void setOptionFromListByEquals(List<WebElement> webElementsList, String option){
        for (WebElement webElement : webElementsList){
            scrollToElement(webElement);
            if (webElement.getText().equals(option)) {
                webElement.click();
                break;
            }
        }
    }

    private void setOptionFromListByContains(List<WebElement> webElementList, String option){
        for (WebElement webElement : webElementList){
            scrollToElement(webElement);
            if (webElement.getText().contains(option)) {
                webElement.click();
                break;
            }
        }
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    }


