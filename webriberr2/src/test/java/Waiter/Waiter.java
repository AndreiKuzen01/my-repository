package Waiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Waiter {

    private static final int WAIT_TIME = 20;

    public static void waitElementVisible(WebDriver driver, WebElement webElement){
        new WebDriverWait(driver, WAIT_TIME)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitListElementVisible(WebDriver driver, List<WebElement> webElements){
        waitElementVisible(driver, webElements.get(0));
    }

//    public static void waitElement(WebDriver driver){
//        new WebDriverWait(driver, WAIT_TIME).until(ExpectedConditions.)
//    }

    public static void waitAttributeValue(WebDriver driver, WebElement webElement, String attribute, String value){
        (new WebDriverWait(driver, WAIT_TIME)).
                until(ExpectedConditions.attributeContains(webElement, attribute, value));
    }
}
