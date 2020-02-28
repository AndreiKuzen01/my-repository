package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CalculatorPage;
import sun.awt.windows.ThemeReader;

import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class First {

    public WebDriver driver;
    public CalculatorPage calculatorPage;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void tearDown(){
        driver.close();
    }

    @Test
    public void first() throws InterruptedException {
        calculatorPage = new CalculatorPage(driver);
        calculatorPage.openPage();
        calculatorPage.outFromFrame();
        Thread.sleep(10000);
        calculatorPage.switchToFrame().setEngine("Compute Engine");
        calculatorPage.switchToFrame().setNumberOfInstance(4)
        .setOperationSystem("Free: Debian, CentOS, CoreOS, Ubuntu, or other User Provided OS")
        .setVMClass("Regular")
        .setInstanceType("n1-standard-8 (vCPUs: 8, RAM: 30GB)")
        .addJPUs()
        .setNumberOfJPU("1");

        //calculatorPage.clickButton();

    }
}