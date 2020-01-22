package subClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendEmail {

    private WebDriver driver;

    private WebDriver linkDriver;

    private WebDriverWait wait;

    public SendEmail(WebDriver driver, WebDriver linkDriver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 20);
        this.linkDriver = linkDriver;
    }

    public SendEmail pressEmailButton (String xpath) {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        return this;
    }

    public String generateAndCopyEmail (String link, String xpath, String value){
        linkDriver.get(link);
        String copyLink = linkDriver.findElement(By.xpath(xpath)).getAttribute(value);
        return copyLink;
    }

    public SendEmail pasteEmail(String copyLink, String xpath){
        WebElement emailField = driver.findElement(By.xpath(xpath));
        emailField.sendKeys(copyLink);
        return this;
    }

}
