package pages;

import Waiter.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CalculatorPage {

    private static final String CALCULATOR_PAGE_URL = "https://cloud.google.com/products/calculator";
    private static final String FRAME_LOCATOR = "//*[@id='cloud-site']/devsite-iframe/iframe";
    private WebDriver driver;

    @FindBy(xpath = "//md-tabs-wrapper//md-tab-item//div[@class='name ng-binding']")
    private List<WebElement> sectionsEngine;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement numberOfInstance;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.os']")
    private WebElement operationSystemContainer;

    @FindBy(xpath = "//div[@class='md-select-menu-container']//md-content/md-option")
    private List<WebElement> operationSystemList;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    private WebElement vmClassContainer;
    //*[@class='md-select-menu-container md-active md-clickable']//md-content/md-option
    @FindBy(xpath = "//*[@id='select_container_70']//md-content/md-option")
    private List<WebElement> vmClassList;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    private WebElement instanceTypeContainer;

    @FindBy(xpath = "//div[class='md-select-menu-container md-leave']//md-optgroup[@label='Standard']/md-option")
    private List<WebElement> instanceTypeList;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs']/div[@md-ink-ripple-checkbox]")
    private WebElement addGPUsCheckbox;

    @FindBy(xpath = "//*[contains(@aria-label,'Number of GPUs')]")
    private WebElement numberOfGpu;

    @FindBy(xpath = "//md-option[contains(@ng-disabled,'minGPU')]")
    private List<WebElement> numberOfGpuList;

    @FindBy(xpath = "//div[contains(text(),'1')]/following-sibling::div/parent::md-option[@ng-repeat='item in listingCtrl.supportedGpuNumbers[listingCtrl.computeServer.gpuType]']")
    private WebElement gpuOption;

    @FindBy(xpath = "//*[@placeholder='GPU type']")
    private WebElement gpuType;

    @FindBy(xpath = "//md-option[contains(@ng-disabled,'checkGpuAvailability')]")
    private List<WebElement> gpuTypeList;

    @FindBy(xpath = "//*[@placeholder='Local SSD']")
    private WebElement localSsd;

    @FindBy(xpath = "//md-option[contains(@ng-repeat,'supportedSsd')]")
    private List<WebElement> ssdList;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.location']")
    private WebElement datacenterLocation;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.loadBalancer.location']//*[@ng-repeat='item in listingCtrl.fullRegionList']")
    private List<WebElement> locationList;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    private WebElement commitedUsage;

    @FindBy(xpath = "//div[@class='md-select-menu-container']//md-content/md-option")
    private List<WebElement> committedUsageList;

    @FindBy(xpath = "//*[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement addToEstimateButton;

    @FindBy(xpath = "//md-list-item[@ng-if='item.items.editHook && item.items.editHook.initialInputs.class']/div")
    private WebElement vmClassInfo;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[3]/div")
    private WebElement instanceTypeInfo;

    @FindBy(xpath = "//md-list-item[@ng-if='item.items.editHook && item.items.editHook.initialInputs.class']/div[1]")
    private WebElement regionInfo;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[5]/div")
    private WebElement localSSdInfo;

    @FindBy(xpath = "//*[@id=compute]/md-list/md-list-item[6]/div")
    private WebElement commitmentTermInfo;

    @FindBy(xpath = "//*[@id='resultBlock']/md-card/md-card-content/div/div/div/h2/b")
    private WebElement totalEstimatedCostInfo;

    public CalculatorPage setEngine(String engineName) {
        setOptionFromListByEquals(sectionsEngine, engineName.toUpperCase());
        return this;
    }

    public CalculatorPage setNumberOfInstance(int number) {
        Waiter.waitElementVisible(driver, numberOfInstance);
        numberOfInstance.click();
        numberOfInstance.sendKeys(String.valueOf(number));
        return this;
    }

    public CalculatorPage setOperationSystem(String operationSystem) {
        operationSystemContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(operationSystemList));
        setOptionFromListByContains(operationSystemList, operationSystem);
        return this;
    }

    public CalculatorPage setVmClass(String vmClass) {
        vmClassContainer.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfAllElements(vmClassList));
        setOptionFromListByContains(vmClassList, vmClass);
        return this;
    }

    public CalculatorPage setInstanceType(String instanceType) {
        instanceTypeContainer.click();
        Waiter.waitListElementVisible(driver, instanceTypeList);
        setOptionFromListByContains(instanceTypeList, instanceType);
        return this;
    }

    public CalculatorPage selectAddGPUCheckbox() {
        Waiter.waitElementVisible(driver, addGPUsCheckbox);
        addGPUsCheckbox.click();
        return this;
    }

    public CalculatorPage setNumberOfGPUs(int number) {
        Waiter.waitElementVisible(driver, numberOfGpu);
        numberOfGpu.click();
        Waiter.waitListElementVisible(driver, numberOfGpuList);
        setOptionFromListByContains(numberOfGpuList, String.valueOf(number));
        return this;
    }

    public CalculatorPage selectGpuType(String gpyTypeValue) {
        Waiter.waitElementVisible(driver, gpuType);
        gpuType.click();
        Waiter.waitListElementVisible(driver, gpuTypeList);
        setOptionFromListByEquals(gpuTypeList, gpyTypeValue);
        return this;
    }

    public CalculatorPage setLocalSsd(String ssdValue) {
        Waiter.waitElementVisible(driver, localSsd);
        scrollToElement(localSsd);
        localSsd.click();
        Waiter.waitListElementVisible(driver, ssdList);
        setOptionFromListByEquals(ssdList, ssdValue);
        return this;
    }

    public CalculatorPage setLocation(String locationValue) {
        Waiter.waitElementVisible(driver, datacenterLocation);
        scrollToElement(datacenterLocation);
        datacenterLocation.click();
        Waiter.waitAttributeValue(driver, datacenterLocation, "aria-expanded", "true");
        setOptionFromListByEquals(locationList, locationValue);
        return this;
    }

    public CalculatorPage setCommitedUsage(String commitedUsgeValue) {
        Waiter.waitElementVisible(driver, commitedUsage);
        commitedUsage.click();
        Waiter.waitListElementVisible(driver, committedUsageList);
        setOptionFromListByEquals(committedUsageList, commitedUsgeValue);
        return this;
    }

    public CalculatorPage clickButtonAddToEstimate() {
        Waiter.waitElementVisible(driver, addToEstimateButton);
        addToEstimateButton.click();
        return this;
    }

    public String getVMClassInfo() {
        return vmClassInfo.getText();
    }

    public String getInstanceTypeInfo() {
        return instanceTypeInfo.getText();
    }

    public String getSsdSpaceInfo() {
        return localSSdInfo.getText();
    }

    public String getCommitmentTerm() {
        return commitmentTermInfo.getText();
    }

    public String getRegionInfo() {
        return regionInfo.getText();
    }

    public String getTotalEstimatedCostInfo() {
        return totalEstimatedCostInfo.getText();
    }


    public CalculatorPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public CalculatorPage openPage() {
        driver.get(CALCULATOR_PAGE_URL);
        return this;
    }

    private void setOptionFromListByEquals(List<WebElement> webElementsList, String option) {
        for (WebElement webElement : webElementsList) {
            scrollToElement(webElement);
            if (webElement.getText().equals(option)) {
                webElement.click();
                break;
            }
        }
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public CalculatorPage switchToFrame(int num) throws InterruptedException {
        Thread.sleep(10000);
        driver.switchTo().frame(num);
        return this;
    }

    public void outFromFrame() {
        driver.switchTo().defaultContent();
    }

    private void setOptionFromListByContains(List<WebElement> webElementList, String option) {
        for (WebElement webElement : webElementList) {
            scrollToElement(webElement);
            if (webElement.getText().contains(option)) {
                webElement.click();
                break;
            }
        }
    }
}
