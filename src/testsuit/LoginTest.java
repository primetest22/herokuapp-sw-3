package testsuit;

import browserfactory.BaseTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class LoginTest extends Utility {
    String baseUrl = "http://the-internet.herokuapp.com/login";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void userSholdLoginSuccessfullyWithValidCredentials() {
        //find username element and send keys
        sendTextToElement(By.id("username"), "tomsmith");

        //find password element and send keys
        sendTextToElement(By.id("password"), "SuperSecretPassword!");

        //find login button and click
        clickOnElement(By.className("radius"));

        String expectedDisplay = "Secure Area";
        String actualDisplay = getTextFromElement(By.xpath("//body[1]/div[2]/div[1]/div[1]/h2[1]"));

        //validate expected and actual message
        Assert.assertEquals("Not Matching", expectedDisplay, actualDisplay);

    }

    @Test
    public void verifyTheUsernameErrorMessage() {
        //find username element and send keys
        sendTextToElement(By.id("username"), "tomsmith1");
        sendTextToElement(By.id("password"), "SuperSecretPassword!");

        clickOnElement(By.className("radius"));

        String expectedDisplay = "Your username is invalid!";
        String actualDisplay = getTextFromElement(By.xpath("//div[@class='flash error']"));

        //use substring method
        String actualSub = actualDisplay.substring(0, 25);

        Assert.assertEquals("Not Matching", expectedDisplay, actualSub);
    }

    @Test
    public void verifyThePasswordErrorMessage() {
        sendTextToElement(By.id("username"), "tomsmith");
        sendTextToElement(By.id("password"), "SuperSecretPassword");

        clickOnElement(By.className("radius"));

        String expectedDisplay = "Your password is invalid!";
        String actualDisplay = getTextFromElement(By.xpath("//div[@class='flash error']"));

        //use substring method
        String actualSub = actualDisplay.substring(0, 25);

        Assert.assertEquals("Not Matching", expectedDisplay, actualSub);
    }

    @After
    public void tearDown() {
        closeBrowser();
    }
}
