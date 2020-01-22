package subClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenCalculator {

    private WebDriver driver;

    private WebDriverWait wait;

    public OpenCalculator(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
    }

    public OpenCalculator openPage() {
        driver.get("https://cloud.google.com/products/calculator/");
        driver.switchTo().frame("myFrame");
        return this;
    }

    public OpenCalculator fillFieldWithNumbers(String xpath, String value) {
        WebElement numberOfInstances = driver.findElement(By.xpath(xpath));
        numberOfInstances.sendKeys(value);
        return this;
    }

    public OpenCalculator fillSelectField(String xpath, String value){
        WebElement instanceTypeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", instanceTypeButton);
        WebElement instanceType = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(value)));
        instanceType.click();
        return this;
    }

    public OpenCalculator markCheckBox(String xpath) {
        WebElement checkBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        checkBox.click();
        return this;
    }

    public OpenCalculator pressButton (String xpath) {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        return this;
    }

    public String senderCost (String xpath){
        String senderCost = driver.findElement(By.xpath(xpath)).getText();
        System.out.println("Sender cost --> " + senderCost);
        return senderCost;
    }

}
