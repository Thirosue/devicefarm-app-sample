import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Takeshi Hirosue
 */
public class SampleTest {

    private RemoteWebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setPlatform(Platform.IOS);
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"),
                capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    public boolean takeScreenshot(final String name) {
        String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
        System.out.println(screenshotDirectory);
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
    }

    @Test
    public void test02() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.name("Add")).click();
        Thread.sleep(2000);
        //driver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[1]/UIATextField[1]")).sendKeys("Book tickets for vacation");
        driver.findElement(By.name("Save")).click();
        Thread.sleep(2000);
        String screenshot2 = null;
        takeScreenshot(screenshot2);
    }
}
