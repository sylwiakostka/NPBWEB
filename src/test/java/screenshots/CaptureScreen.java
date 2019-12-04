package screenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import utilities.DriverFactory;
import utilities.DriverType;
import utilities.NowSuchDriverException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CaptureScreen extends BasePage {

    public CaptureScreen(WebDriver driver) {
        super(driver);
    }

    public void takeScreenshot(WebElement element, String elementName) throws IOException, NowSuchDriverException {
        String screenshotFolder = "C://Users//user//Desktop//NPB//src//test//java//NPB//tests//ScreenshotsToCompareInTests//";
//        WebDriver driver = DriverFactory.getDriver(DriverType.CHROME);

        Screenshot imageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, element);
        ImageIO.write(imageScreenshot.getImage(), "png", new File(screenshotFolder+elementName+".png"));
        File f = new File(screenshotFolder+elementName+".png");
        if (f.exists()) {
            System.out.println("Image File Captured");
        } else {
            System.out.println("Image File NOT exist");
        }
    }


    public void compareImages (String expectedImageName, WebElement NameOfElementToTakeScreenshot) throws IOException {
        String screenshotFolder = "C://Users//user//Desktop//NPB//src//test//java//NPB//tests//ScreenshotsToCompareInTests//";
        BufferedImage expectedImage = ImageIO.read(new File(screenshotFolder+expectedImageName+".png"));
        Screenshot pageImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).takeScreenshot(driver, NameOfElementToTakeScreenshot);
        BufferedImage actualImage = pageImageScreenshot.getImage();

        ImageDiffer imgDiff = new ImageDiffer();
        ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);
        if(diff.hasDiff()==true)
        {
            System.out.println("Images are Not Same");
        }
        else {
            System.out.println("Images are Same");
        }

    }


}

