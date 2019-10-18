package utilities;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.Point;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CaptureScreenshotOfElement {

    private final String screenshotFolder = "C://Users//user//Desktop//NPB//src//test//java//NPB//tests//ScreenshotsToCompareInTests//screenshot.png";
    protected WebDriver driver;

    public CaptureScreenshotOfElement(WebDriver driver) {
        this.driver = driver;
    }

    public File takeScreenshot(WebElement element) throws IOException {


        File screen = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Point p = element.getLocation();

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        Rectangle rectangle = new Rectangle(width,height);

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rectangle.width,
               rectangle.height);

        ImageIO.write(dest, "png", screen);
        FileUtils.copyFile(screen, new File(screenshotFolder));


        return screen;

    }
}
