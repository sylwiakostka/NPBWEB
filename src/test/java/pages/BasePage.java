package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

public class BasePage {

    protected WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    private static final int WAIT_TIMEOUT = 20;

    protected void waitForVisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForTextToBePresentInElementValue(WebElement element, String textInElement) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, textInElement));
    }

    protected void waitForAtributeOfElement(WebElement element, String attribute, String valueOfAttribute) {
        wait.until(ExpectedConditions.attributeContains(element, attribute, valueOfAttribute));
    }

//    protected void waitForVisibilityOfElements(List<WebElement> elements) {
//        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
//    }

    protected void waitForVisibilityOfElements(List<WebElement> elements) {

        FluentWait<WebDriver> fwait = new FluentWait<WebDriver>(driver)
                .withTimeout(3, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class);


        fwait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }


    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    protected void waitForPresenceOfElement(WebElement element) {
        WebElement element1 = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return element;
            }
        });
    }

    protected void waitForPresenceOfElements(List<WebElement> elements) {
        List<WebElement> elements1 = wait.until(new ExpectedCondition<List<WebElement>>() {
            public List<WebElement> apply(WebDriver d) {
                return elements;
            }
        });
    }

    protected void waitUntilElementNotDisplayed(WebElement element) {
        Boolean element1 = wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    element.isDisplayed();
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        });
    }

    protected void waitUntilElementsNotDisplayed(List<WebElement> elements) {
        Boolean element1 = wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    for (WebElement element : elements) {
                        element.isDisplayed();
                    }
                    return false;
                } catch (NoSuchElementException e) {
                    return true;
                } catch (StaleElementReferenceException f) {
                    return true;
                }
            }
        });
    }


    protected void findElementFromUlListByTextAndClick(String textToFind) throws InterruptedException {
        List<WebElement> liList = driver.findElements(By.xpath("//ul[@class='open']//li"));
        Thread.sleep(1000);
        waitForVisibilityOfElements(liList);
        for (WebElement li : liList) {
            try {
                String text = li.getText();
                if (text.equals(textToFind)) {
                    li.click();
                }
            } catch (StaleElementReferenceException e) {
            }

        }
    }

    protected void findElementFromUlListByTextAndClickByIndex(int index) throws InterruptedException {
        List<WebElement> liList = driver.findElements(By.xpath("//ul[@class='open']//li"));
        Thread.sleep(1000);
        waitForVisibilityOfElements(liList);
        WebElement li = liList.get(index);
        try {
            li.click();
        } catch (StaleElementReferenceException e) {
            li.click();
        }
    }

}




