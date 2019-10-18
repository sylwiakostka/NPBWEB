package utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import pages.BasePage;

public class JsHelper extends BasePage {

    public JsHelper(WebDriver driver) {
        super(driver);
    }

    public static void setAttribute(WebElement element, String attributeName, String value) {
        WrapsDriver wrappedElement = (WrapsDriver) element;
        JavascriptExecutor js=(JavascriptExecutor)wrappedElement.getWrappedDriver();
        js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,attributeName,value);
    }

    public void scrollDownPage(){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void scrollUpPage(){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public void scrollRight(){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(1000,0)", "");
    }

    public void scrollLeft(){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(-1000,0)");
    }




}
