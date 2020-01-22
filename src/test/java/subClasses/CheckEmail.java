package subClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckEmail {

    private WebDriver linkDriver;

    private WebDriverWait wait;

    public CheckEmail(WebDriver linkDriver) {
        this.wait = new WebDriverWait(linkDriver, 20);
        this.linkDriver = linkDriver;
    }

    public CheckEmail openEmail(String xpath) throws Exception {
        WebElement openEmailTab = null;
        for (int i = 1; i<30; i++){
            try {
                openEmailTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
                break;
            }catch (Exception ignored){
                System.out.println("Iteration: " + i);
            }
            Thread.sleep(10000);
        }

        JavascriptExecutor executor = (JavascriptExecutor) linkDriver;
        if (openEmailTab == null){
            throw new Exception("Failed to wait for emeil");
        }
        executor.executeScript("arguments[0].click();", openEmailTab);
        return this;
    }

    public String recipientEmailCost(String xpath){
        WebElement recipientCostElement = linkDriver.findElement(By.xpath(xpath));
        ((JavascriptExecutor)linkDriver).executeScript("arguments[0].scrollIntoView();", recipientCostElement);
        String recipientCost = recipientCostElement.getText();
        System.out.println("Recipient cost --> " + recipientCost);
        return recipientCost;
    }
}
