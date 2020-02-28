package tests;

import WebDriverSettings.DriverSettings;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CalculatorPage;
import pages.HomePage;
import pages.SearchResultPage;

public class GoogleCalculatorTest extends DriverSettings {

    String request = "Google Cloud Platform Pricing Calculator";

    public HomePage homePage;
    public SearchResultPage searchResultPage;
    public CalculatorPage calculatorPage;

    @Test
    public void first() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.openPage();
        homePage.clickSearchButton();
        searchResultPage = homePage.searchForTerms(request);
        calculatorPage = searchResultPage.clickSearchElement();
        calculatorPage.outFromFrame();
        calculatorPage.switchToFrame(0).setEngine("Compute Engine");
        calculatorPage.switchToFrame(0).setNumberOfInstance(4)
                .setOperationSystem("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
                .setVmClass("Regular")
                .setInstanceType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
                .selectAddGPUCheckbox()
                .setNumberOfGPUs(1)
                .selectGpuType("NVIDIA Tesla V100")
                .setLocalSsd("2x375 GB")
                .setLocation("Frankfurt (europe-west3)")
                .setCommitedUsage("1 Year")
                .clickButtonAddToEstimate();
        Assert.assertEquals("VM class: regular", calculatorPage.getVMClassInfo());
        Assert.assertEquals("Instance type: n1-standard-8",calculatorPage.getInstanceTypeInfo() );
        Assert.assertEquals("Region: Frankfurt", calculatorPage.getRegionInfo());
        Assert.assertEquals("Total available local SSD space 2x375 GB", calculatorPage.getSsdSpaceInfo());
        Assert.assertEquals("Commitment term: 1 Year", calculatorPage.getCommitmentTerm());
        Assert.assertEquals("Total Estimated Cost: USD 1,082.77 per 1 month", calculatorPage.getTotalEstimatedCostInfo());
    }
}
