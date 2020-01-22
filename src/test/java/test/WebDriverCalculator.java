package test;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import subClasses.CheckEmail;
import subClasses.OpenCalculator;
import subClasses.SendEmail;

import static subClasses.ConfigData.*;

public class WebDriverCalculator {

    private WebDriver driver;

    private WebDriver linkDriver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        DesiredCapabilities chrome_cap = DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        chrome_cap.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(chrome_cap);

        DesiredCapabilities chrome_cap_second_window = DesiredCapabilities.chrome();
        ChromeOptions options_second = new ChromeOptions();
        options_second.addArguments("incognito");
        chrome_cap_second_window.setCapability(ChromeOptions.CAPABILITY, options);
        linkDriver = new ChromeDriver(chrome_cap_second_window);
    }

    @Test(description = "Just first rest, JIRA binding can be here")
    public void commonSearchTermResults() throws Exception {

        OpenCalculator openCalculator = new OpenCalculator(driver);
        OpenCalculator page = openCalculator.openPage();

        String senderCostSTR = page.fillFieldWithNumbers(NUMBER_OF_INSTANCES_XPATH,NUMBER_OF_INSTANCES_VALUE)
            .fillSelectField(OPERATION_SYSTEM_XPATH,OPERATION_SYSTEM_VALUE)
            .fillSelectField(VIR_MACHINE_CLASS_XPARH,VIR_MACHINE_CLASS_VALUE)
            .fillSelectField(MACHINE_TYPE_XPATH,MACHINE_TYPE_VALUE)
            .markCheckBox(CHECKBOX_ADD_GPUS_XPATH)
            .fillSelectField(NUMBER_OF_GPUS_XPATH,NUMBER_OF_GPUS_VALUE)
            .fillSelectField(GPU_TYPE_XPATH,GPU_TYPE_VALUE)
            .fillSelectField(LOCAL_SSD_XPATH,LOCAL_SSD_VALUE)
            .fillSelectField(DATACENTER_LOCATION_XPATH,DATACENTER_LOCATION_VALUE)
            .fillSelectField(COMMITTED_USAGE_XPATH,COMMITTED_USAGE_VALUE)
            .pressButton(ADD_TO_ESTIMATE_BUTTON_XPATH)
            .pressButton(ESTIMATE_BUTTON_XPATH)
            .senderCost(SENDER_COST_XPATH);

        SendEmail sendEmail = new SendEmail(driver, linkDriver);
        String pasteEmail = sendEmail.generateAndCopyEmail(LINK_GENERATE_EMAIL,LINK_XPATH,GET_EMAIL_BY_VALUE);
        sendEmail.pasteEmail(pasteEmail,EMAIL_FIELD_XPATH);
        sendEmail.pressEmailButton(SEND_EMAIL_BUTTON_XPATH);

        CheckEmail checkEmail = new CheckEmail(linkDriver);
        String emailCostSTR = checkEmail.openEmail(OPEN_EMAIL_TAB_XPATH)
                  .recipientEmailCost(RECIPIENT_COST_ELEMENT_XPATH);

        Assert.assertTrue(senderCostSTR.contains(emailCostSTR));

    }

    @AfterMethod(alwaysRun = true)
    public void browseTearDown() {
        driver.quit();
        linkDriver.quit();
    }
}
