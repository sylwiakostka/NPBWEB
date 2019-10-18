package utilities;

import java.awt.*;
import java.awt.image.PixelGrabber;

public class CompareScreenshots {

    public enum Result {
        Matched, SizeMismatch, PixelMismatch
    }

    public static Result CompareImage(String baseFileName) {
        Result compareResult = Result.PixelMismatch;
        Image baseImage = Toolkit.getDefaultToolkit().getImage("C://Users//user//Desktop//NPB//src//test//java//NPB//tests//ScreenshotsToCompareInTests//"+baseFileName);
        String actualScreenshot = "C://Users//user//Desktop//NPB//src//test//java//iTaxiPassanger//tests//screenshotsToCompareInTests//screenshot.png";
        Image actualImage = Toolkit.getDefaultToolkit().getImage(actualScreenshot);
        try {
            PixelGrabber baseImageGrab = new PixelGrabber(baseImage, 0, 0, -1, -1, false);
            PixelGrabber actualImageGrab = new PixelGrabber(actualImage, 0, 0, -1, -1, false);

            int[] baseImageData = null;
            int[] actualImageData = null;

            if (baseImageGrab.grabPixels()) {
                int width = baseImageGrab.getWidth();
                int height = baseImageGrab.getHeight();
                baseImageData = new int[width * height];
                baseImageData = (int[]) baseImageGrab.getPixels();
            }

            if (actualImageGrab.grabPixels()) {
                int width = actualImageGrab.getWidth();
                int height = actualImageGrab.getHeight();
                actualImageData = new int[width * height];
                actualImageData = (int[]) actualImageGrab.getPixels();
            }

            if ((baseImageGrab.getHeight() != actualImageGrab.getHeight()) || (baseImageGrab.getWidth() != actualImageGrab.getWidth()))
                compareResult = Result.SizeMismatch;
            else if (java.util.Arrays.equals(baseImageData, actualImageData))
                compareResult = Result.Matched;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compareResult;
    }

}
